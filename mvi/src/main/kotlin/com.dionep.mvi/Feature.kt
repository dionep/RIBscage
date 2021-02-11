package com.dionep.mvi

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*

@Suppress("UNCHECKED_CAST")
open class Feature<out State, Cmd, Msg: Any, out News> (
    initialState: State,
    private val initialMessages: Set<Msg> = setOf(),
    private val reducer: (Msg, State) -> Update<State, Cmd>,
    private val commandHandler: suspend CoroutineScope.(Cmd) -> Flow<SideEffect<Msg, News>>,
    bootstrapper: Set<Flow<Msg>> = setOf()
) {

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Default + job)
    fun getScope() = coroutineScope

    private val stateChannel = MutableSharedFlow<State>(replay = 1)
    private val msgChannel= MutableSharedFlow<Msg>(extraBufferCapacity = 64)
    private val commandChannel = MutableSharedFlow<Cmd>(extraBufferCapacity = 64)
    private val newsChannel = BroadcastChannel<News>(BUFFERED)

    init {
        coroutineScope.launch {
            stateChannel.emit(initialState)
        }
        launchCmdChannel()
        bootstrapper.forEach {
            coroutineScope.launch(Dispatchers.IO) {
                it.collect {
                    accept(it)
                }
            }
        }
    }

    private fun launchCmdChannel() {
        coroutineScope.launch(Dispatchers.IO) {
            commandChannel
                .onStart { launchMsgChannel() }
                .collect { cmd ->
                    commandHandler.invoke(coroutineScope, cmd).collect { (msg, news) ->
                        msg?.let { msgChannel.emit(msg) }
                        news?.let { newsChannel.waitingSend(this, news) }
                    }
                }
        }
    }

    private fun launchMsgChannel() {
        coroutineScope.launch(Dispatchers.IO) {
            msgChannel
                .onStart {
                    initialMessages.forEach { emit(it) }
                }
                .collect { msg ->
                    val (state, cmd) = reducer.invoke(msg, stateChannel.first())
                    state?.let { stateChannel.emit(it) }
                    cmd?.let { commandChannel.emit(it) }
                }
        }
    }

    open fun accept(msg: Any) {
        coroutineScope.launch { (msg as? Msg)?.let { msgChannel.emit(it) } }
    }

    open fun dispose() {
        job.complete()
        newsChannel.close()
    }

    val stateFlow: SharedFlow<State>
        get() = stateChannel.asSharedFlow()

    val newsReceiveChannel: ReceiveChannel<News>
        get() = newsChannel.openSubscription()

}

fun <T> BroadcastChannel<T>.safeSend(scope: CoroutineScope, item: T) {
    item?.let {
        if (!isClosedForSend)
            scope.launch {
                try {
                    send(it)
                } catch (e: ClosedSendChannelException) {}
            }
    }
}

fun <T> BroadcastChannel<T>.waitingSend(
    coroutineScope: CoroutineScope,
    item: T,
    cancellationTime: Long = 100L
) {
    coroutineScope.launch {
        var delayTime = 0L
        while (isClosedForSend) {
            println(delayTime)
            delay(50)
            delayTime+=50L
            if (delayTime == cancellationTime) cancel()
        }
    }.invokeOnCompletion {
        safeSend(coroutineScope, item)
    }
}