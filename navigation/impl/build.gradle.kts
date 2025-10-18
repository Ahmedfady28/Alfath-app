import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.junit5)
    alias(libs.plugins.kotlin.serialization)
}

junitPlatform {
    configurationParameter("junit.jupiter.testinstance.lifecycle.default", "per_class")
}

android {
    namespace = "com.navigation.impl"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }
}

dependencies {
    // platforms
    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.koin.bom))

    // libs
    implementation(libs.koin.compose.viewmodel.navigation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.ui)
    //    navigation 3
//    implementation(libs.navigation3.runtime)
//    implementation(libs.navigation3.ui)
//    implementation(libs.navigation3.lifecycle.viewmodel)
//    implementation(libs.navigation3.material3.adaptive)

    implementation(libs.androidx.navigation.compose)
//    implementation(libs.android.junit5)


    // refs
    implementation(projects.navigation.api)
    implementation(projects.features.home.ui)
    implementation(projects.features.profile.ui)

    // tests
//    testImplementation(libs.coroutines.test)
//    testImplementation(libs.mockk)
//    testImplementation(libs.turbine)
//    testImplementation(libs.junit.jupiter)
//    testImplementation(libs.kotest.assertion.core)
    testImplementation(libs.bundles.test)

    androidTestImplementation(libs.mockk.android)



//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.kotlinx.serialization.json)
//    implementation(libs.kotlinx.serialization.core)
//    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
//    testImplementation(kotlin("test"))

    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

//    JUNIT 5
    testImplementation(libs.junit5.jupiter.api)
    testImplementation(libs.junit5.jupiter.params)
    testRuntimeOnly(libs.junit5.jupiter.engine)
    androidTestImplementation(libs.androidx.compose.ui.test.android)

    androidTestImplementation(libs.junit5.jupiter.api)
    testImplementation(kotlin("test"))
}