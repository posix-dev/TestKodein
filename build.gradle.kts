import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id(GradlePluginId.GRADLE_VERSION_PLUGIN) version GradlePluginVersion.GRADLE_VERSION_PLUGIN
}


buildscript {
    repositories {
        maven { url = uri("https://jitpack.io") }
        google()
        jcenter()
    }
    dependencies {
        classpath(GradlePluginDependency.ANDROID_GRADLE)
        classpath(kotlin(GradlePluginDependency.KOTLIN_GRADLE_PLUGIN))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }  //for example
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {

    // Example 1: reject all non stable versions
    rejectVersionIf {
        isNonStable(candidate.version)
    }

    // Example 2: disallow release candidates as upgradable versions from stable versions
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }

    // Example 3: using the full syntax
    resolutionStrategy {
        componentSelection {
            all {
                if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                    reject("Release candidate")
                }
            }
        }
    }

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}
