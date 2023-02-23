import com.android.build.gradle.BaseExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

apply(plugin = "com.github.ben-manes.versions")
apply(plugin = "io.gitlab.arturbosch.detekt")

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(AndroidDependencies.gradle)
        classpath(AndroidDependencies.kotlin)
        classpath(AndroidDependencies.dependencyUpdate)
        classpath(AndroidDependencies.detekt)
        classpath(AndroidDependencies.navigationSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    afterEvaluate {
        (extensions.findByName("android") as? BaseExtension)?.apply {
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }
}

tasks {
    register("clean") {
        delete(rootProject.buildDir)
    }

    withType<DependencyUpdatesTask> {
        rejectVersionIf {
            isNonStable(candidate.version) && !isNonStable(currentVersion)
        }
    }

    withType<Detekt>().configureEach {
        buildUponDefaultConfig = true
        parallel = true
        reports {
            jvmTarget = "11"
            html.required.set(true)
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}