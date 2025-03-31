plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.bt_viewpager_cricleindicator"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bt_viewpager_cricleindicator"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.circleindicator)
    implementation(libs.autoimageslider)
    implementation(libs.glide)
    implementation(libs.retrofit) // Retrofit core
    implementation(libs.converterGson) // Convert JSON sang Object Java

    annotationProcessor(libs.glideCompiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}