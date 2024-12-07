plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.picpay.desafio.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.picpay.desafio.android"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".dev"
            buildConfigField("String", "URL_WEBSERVICE", "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        register("dev") {
            initWith(getByName("debug"))
        }

        register("pilot") {
            initWith(getByName("release"))
            buildConfigField("String", "URL_WEBSERVICE", "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }

        register("production") {
            initWith(getByName("release"))
            buildConfigField("String", "URL_WEBSERVICE", "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
//    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.21")

    implementation("androidx.core:core-ktx:1.2.0")

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    implementation("com.google.android.material:material:1.12.0")

    implementation("io.insert-koin:koin-core:3.5.0")
    implementation("io.insert-koin:koin-android:3.5.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    implementation("io.reactivex.rxjava2:rxjava:2.2.17")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.squareup.retrofit2:retrofit:2.7.1")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.7.1")
    implementation("com.squareup.retrofit2:converter-gson:2.7.1")
    implementation("com.squareup.okhttp3:okhttp:4.3.1")
    implementation("com.squareup.okhttp3:mockwebserver:4.3.1")

    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("de.hdodenhof:circleimageview:3.0.0")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:2.27.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation("io.mockk:mockk:1.13.7")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.5.0")

    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test:core-ktx:1.6.1")
}
