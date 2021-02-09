plugins {
    application
    kotlin("jvm")
//    kotlin("plugin.serialization")
}

//application {
//    mainClassName = "com.ribsage.backend.MainKt"
//}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks {

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    create("stage") {
        dependsOn(getByName("installDist"))
    }

}