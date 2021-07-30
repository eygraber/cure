package com.eygraber.cares.samples.simple_clock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
  navWindow.update {
    add(SimpleClockFactoryKey.Clock)
  }

  application {
    Window(onCloseRequest = ::exitApplication, title = "SimpleClock") {
      MaterialTheme(
        colors = darkColors()
      ) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              color = MaterialTheme.colors.background
            )
        ) {
          AppContent()
        }
      }
    }
  }
}