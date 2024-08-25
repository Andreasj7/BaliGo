plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
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
        mlModelBinding = true
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.tensorflow.lite.metadata)
    implementation(libs.androidx.recyclerview)
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
        implementation("com.google.android.material:material:1.4.0")
        implementation("org.tensorflow:tensorflow-lite-gpu:2.12.0")
        implementation("org.tensorflow:tensorflow-lite:2.12.0")
        implementation("org.tensorflow:tensorflow-lite-support:0.4.3")
        implementation ("androidx.recyclerview:recyclerview:1.2.1")
        implementation("com.github.bumptech.glide:glide:4.13.0")
        annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")
    }
}