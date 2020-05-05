private object TestLibraryVersion {
    const val JUNIT = "4.13"
    const val TEST_RUNNER = "1.0.2"
    const val ESPRESSO_CORE = "3.0.2"
    const val ANDROID_X_JUNIT = "1.1.1"
}

object TestLibraryDependency {
    const val JUNIT = "junit:junit:${TestLibraryVersion.JUNIT}"
    const val TEST_RUNNER = "com.android.support.test:runner:${TestLibraryVersion.TEST_RUNNER}"
    const val ESPRESSO_CORE = "com.android.support.test.espresso:espresso-core:${TestLibraryVersion.ESPRESSO_CORE}"
    const val ANDROID_X_JUNIT = "androidx.test.ext:junit:${TestLibraryVersion.ANDROID_X_JUNIT}"
}
