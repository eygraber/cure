plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("org.jetbrains.compose")
  id("kotlinx-atomicfu")
  detekt
  `detekt-hotfix`
  publish
}

kotlin {
  explicitApi()

  jvm()

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(kotlin("stdlib-common"))

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

        api("org.jetbrains.kotlinx:atomicfu:0.16.2")

        api(compose.runtime)
        api(compose.foundation)
        api(compose.material)
        api(compose.materialIconsExtended)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
  }
}
