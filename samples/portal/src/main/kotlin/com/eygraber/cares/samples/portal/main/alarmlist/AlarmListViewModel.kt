package com.eygraber.cares.samples.portal.main.alarmlist

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.eygraber.cares.VM
import com.eygraber.cares.samples.portal.appPortals
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.UUID

class AlarmListViewModel : VM<AlarmListState> {
  @Stable
  private class MutableAlarmListAlarm(
    id: String,
    isEnabled: Boolean,
    time: String
  ) : AlarmListAlarm {
    override var id by mutableStateOf(id)
    override var isEnabled by mutableStateOf(isEnabled)
    override var time by mutableStateOf(time)
  }

  private val mutableState = @Stable object : AlarmListState {
    override var alarms by mutableStateOf(
      listOf(
        MutableAlarmListAlarm(
          id = UUID.randomUUID().toString(),
          isEnabled = true,
          time = LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
        )
      )
    )
  }
  override val state = mutableState

  fun backClicked() {
    appPortals.withTransaction {
      backstack.pop()
    }
  }

  fun addAlarmClicked() {
    mutableState.alarms = mutableState.alarms + MutableAlarmListAlarm(
      id = UUID.randomUUID().toString(),
      isEnabled = true,
      time = LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    )
  }

  fun alarmEnabledChanged(alarm: AlarmListAlarm) {
    mutableState
      .alarms
      .find { it.id == alarm.id }
      ?.let {
        it.isEnabled = !it.isEnabled
      }
  }
}