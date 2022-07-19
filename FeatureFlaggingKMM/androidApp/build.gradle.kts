plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.dedan.featureflaggingkmm.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.navigation:navigation-compose:2.5.0")
    implementation ("androidx.activity:activity-compose:1.6.0-alpha05")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.1.1")
    // Material Design
    implementation("androidx.compose.material:material:1.1.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    implementation("io.coil-kt:coil-compose:2.1.0")

}