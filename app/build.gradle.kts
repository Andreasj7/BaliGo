plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id 'kotlin-parcelize'
}

android {
    namespace = "com.example.baligo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.baligo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        mlModelBinding = true
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.tensorflow.lite.metadata)
    implementation(libs.kotlin.parcelize.runtime)
    dependencies {
        // ... your other dependencies ...

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)

        // Add this line
        implementation("com.google.android.material:material:1.12.0")
        implementation("org.tensorflow:tensorflow-lite:2.12.0")
        implementation("org.tensorflow:tensorflow-lite-support:0.4.3")
        implementation ("org.tensorflow:tensorflow-lite-task-vision:0.2.0")
        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.0") // Use the appropriate version
        implementation ("androidx.core:core-ktx:1.9.0") // Or your latest version
    }
}