package com.eygraber.cares.samples.simpleportal

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.singleWindowApplication
import com.eygraber.cares.portal.PortalTransitions
import com.eygraber.cares.portal.Portals
import com.eygraber.cares.portal.push
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.swing.UIManager

enum class PortalKey {
  One,
  Two,
  Three,
  Four,
  Five,
  Six,
  Seven,
  Eight,
  Nine,
  Ten
}

@Composable
fun NumberBox(text: String) {
  Box(modifier = Modifier.fillMaxSize()) {
    Text(text, modifier = Modifier.align(Alignment.Center))
  }
}

@OptIn(DelicateCoroutinesApi::class) fun main() {
  val portals = Portals<PortalKey>(
    defaultTransitions = PortalTransitions(
      enter = slideInHorizontally(
        animationSpec = tween(1000),
        initialOffsetX = { it * 2 }
      ),
      exit = slideOutHorizontally(
        animationSpec = tween(1000),
        targetOffsetX = { it * 2 }
      ),
      restoreFromBackstack = fadeIn(
        animationSpec = tween(1000)
      ),
      sendToBackstack = fadeOut(
        animationSpec = tween(1000)
      )
    )
  )

  GlobalScope.launch {
    portals.updates().collect {
      println(portals.size)
    }
  }

  GlobalScope.launch {
    delay(500)

    portals.addTen()

    delay(2500)

    portals.withTransaction {
      backstack.clear(suppressTransitions = false) { key ->
        val initialWaveDuration = 3000
        val secondWaveDuration = 6000
        val lastWaveDuration = 8000

        when(key) {
          PortalKey.One -> PortalTransitions(
            enter = EnterTransition.None,
            exit = fadeOut(
              animationSpec = tween(durationMillis = lastWaveDuration)
            )
          )

          PortalKey.Two -> PortalTransitions(
            enter = EnterTransition.None,
            exit = shrinkOut(
              animationSpec = tween(durationMillis = lastWaveDuration)
            )
          )

          PortalKey.Three -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutVertically(
              animationSpec = tween(durationMillis = secondWaveDuration),
              targetOffsetY = { -it }
            )
          )

          PortalKey.Four -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutVertically(
              animationSpec = tween(durationMillis = secondWaveDuration),
              targetOffsetY = { it * 2 }
            )
          )

          PortalKey.Five -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutHorizontally(
              animationSpec = tween(durationMillis = secondWaveDuration),
              targetOffsetX = { -it }
            )
          )

          PortalKey.Six -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutHorizontally(
              animationSpec = tween(durationMillis = secondWaveDuration),
              targetOffsetX = { it * 2 }
            )
          )

          PortalKey.Seven -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutVertically(
              animationSpec = tween(durationMillis = initialWaveDuration),
              targetOffsetY = { -it }
            )
          )

          PortalKey.Eight -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutVertically(
              animationSpec = tween(durationMillis = initialWaveDuration),
              targetOffsetY = { it * 2 }
            )
          )

          PortalKey.Nine -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutHorizontally(
              animationSpec = tween(durationMillis = initialWaveDuration),
              targetOffsetX = { -it }
            )
          )

          PortalKey.Ten -> PortalTransitions(
            enter = EnterTransition.None,
            exit = slideOutHorizontally(
              animationSpec = tween(durationMillis = initialWaveDuration),
              targetOffsetX = { it * 2 }
            )
          )
        }
      }
    }
  }

  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
  singleWindowApplication(title = "Simple Portal") {
    MaterialTheme(
      colors = darkColors(
        primary = Color(0xFFBB86FC),
        primaryVariant = Color(0xFF3700B3),
        secondary = Color(0xFF03DAC5)
      )
    ) {
      Surface(
        modifier = Modifier.fillMaxSize()
      ) {
        portals.render()
      }
    }
  }
}

suspend fun Portals<PortalKey>.addTen() {
  val addDelay = 1000L

  withTransaction {
    backstack.push(PortalKey.One) {
      add(PortalKey.One) {
        NumberBox("1")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Two) {
      detachFromComposition(PortalKey.One)

      add(PortalKey.Two) {
        NumberBox("2")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Three) {
      detachFromComposition(PortalKey.Two)

      add(PortalKey.Three) {
        NumberBox("3")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Four) {
      detachFromComposition(PortalKey.Three)

      add(PortalKey.Four) {
        NumberBox("4")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Five) {
      detachFromComposition(PortalKey.Four)

      add(PortalKey.Five) {
        NumberBox("5")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Six) {
      detachFromComposition(PortalKey.Five)

      add(PortalKey.Six) {
        NumberBox("6")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Seven) {
      detachFromComposition(PortalKey.Six)

      add(PortalKey.Seven) {
        NumberBox("7")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Eight) {
      detachFromComposition(PortalKey.Seven)

      add(PortalKey.Eight) {
        NumberBox("8")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Nine) {
      detachFromComposition(PortalKey.Eight)

      add(PortalKey.Nine) {
        NumberBox("9")
      }
    }
  }

  delay(addDelay)

  withTransaction {
    backstack.push(PortalKey.Ten) {
      detachFromComposition(PortalKey.Nine)

      add(PortalKey.Ten) {
        NumberBox("10")
      }
    }
  }
}