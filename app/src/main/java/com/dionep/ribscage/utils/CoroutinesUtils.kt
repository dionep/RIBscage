package com.dionep.ribscage.utils

import kotlinx.coroutines.Deferred

suspend fun <T : Any, R : Any> Deferred<T>.awaitFolding(
    onSuccess: (T) -> R,
    onFailure: (Throwable) -> R
) = runCatching { await() }
    .fold(onSuccess = { onSuccess.invoke(it) }, onFailure = { onFailure.invoke(it) })