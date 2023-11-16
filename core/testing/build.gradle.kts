@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.com.android.library)
  alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
  namespace = "com.sadri.testing"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  implementation(projects.core.data)
  implementation(projects.core.domain)
  implementation(projects.core.model)
  implementation(projects.core.common)

  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  api(libs.junit)
  api(libs.androidx.test.ext.junit)
  api(libs.espresso.core)
  api(libs.kotlinx.coroutines.test)
  api(libs.turbine)
}