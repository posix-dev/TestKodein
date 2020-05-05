object AndroidConfig {
    const val COMPILE_SDK_VERSION = 29
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 29
    const val BUILD_TOOLS_VERSION = "29.0.0"

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val APPLICATION_ID = "uptop.me.testcoroutine"
    const val TEST_INSTRUMENTATION_RUNNER = "android.support.test.runner.AndroidJUnitRunner"
}

interface BuildType {

    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isMinifyEnabled: Boolean
    val applicationIdSuffix: String
        get() = ".debug"
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    const val DEBUGGABLE = true
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = false
    override val applicationIdSuffix = ""
    const val DEBUGGABLE = false
}

object TestOptions {
    const val IS_RETURN_DEFAULT_VALUES = true
}
