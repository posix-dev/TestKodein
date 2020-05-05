object GradlePluginVersion {
    const val ANDROID_GRADLE = "3.6.3"
    const val KTLINT_GRADLE = "9.1.1"
    const val DETEKT = "1.4.0"
    const val GRADLE_VERSION_PLUGIN = "0.28.0"
    const val KOTLIN = CoreVersion.KOTLIN
    const val SAFE_ARGS = CoreVersion.NAVIGATION
}

object GradlePluginId {
    const val KTLINT_GRADLE = "org.jlleitschuh.gradle.ktlint"
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_JVM = "jvm"
    const val KOTLIN_ANDROID = "android"
    const val KOTLIN_ANDROID_EXTENSIONS = "android.extensions"
    const val GRADLE_VERSION_PLUGIN = "com.github.ben-manes.versions"
}

object GradlePluginDependency {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val KOTLIN_GRADLE_PLUGIN = "gradle-plugin:${GradlePluginVersion.KOTLIN}"
    const val GRADLE_VERSION_PLUGIN = "com.github.ben-manes:gradle-versions-plugin:${GradlePluginVersion.GRADLE_VERSION_PLUGIN}"

}
