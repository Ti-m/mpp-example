import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id( "com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
}

group = "org.greeting"
version = 1.0

android {
    compileSdkVersion(27)
    defaultConfig {
        minSdkVersion(15)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Specify Kotlin/JVM stdlib dependency.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")

    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    androidTestImplementation("junit:junit:4.12")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

kotlin {
   android("androidLib")

   val iosTarget = iosArm64("ios")
   iosTarget.binaries {
      framework {
         embedBitcode("disable")
      }
   }

   sourceSets {
      commonMain {
         dependencies {
            implementation( "org.jetbrains.kotlin:kotlin-stdlib-common")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.3")
         }
      }
      commonTest {
         dependencies {
            implementation( "org.jetbrains.kotlin:kotlin-test-common")
             implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
             implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
         }
      }
       sourceSets["iosMain"].dependencies {
           implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")
       }
    }
}

tasks.register("copyFramework") {
    val buildType = project.findProperty("kotlin.build.type") as? String ?: "DEBUG"
    dependsOn("link${buildType.toLowerCase().capitalize()}FrameworkIos")

    doLast {
        val srcFile = (kotlin.targets["ios"] as KotlinNativeTarget).binaries.getFramework(buildType).outputFile
        val targetDir = project.property("configuration.build.dir")!!
        copy {
            from(srcFile.parent)
            println("copy from ${srcFile.parent}")
            into(targetDir)
            println("to $targetDir")
            include( "greeting.framework/**")
            include("greeting.framework.dSYM")
        }
    }
}

tasks.register("iosTest")  {
    val  device = project.findProperty("iosDevice") as? String ?: "iPhone 8"
    dependsOn("linkDebugTestIos")
    group = JavaBasePlugin.VERIFICATION_GROUP
    description = "Runs tests for target 'ios' on an iOS simulator"

    doLast {
        val  binary = (kotlin.targets["ios"] as KotlinNativeTarget).binaries.getTest("DEBUG").outputFile
        exec {
            commandLine("xcrun", "simctl", "spawn", "--standalone", device, binary.absolutePath)
        }
    }
}
