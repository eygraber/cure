package com.eygraber.cares.portal.internal

import androidx.compose.runtime.Immutable
import com.eygraber.cares.portal.PortalRender
import com.eygraber.cares.portal.PortalTransitions

@Immutable
internal data class PortalEntry<PortalKey>(
  val key: PortalKey,
  val wasContentPreviouslyVisible: Boolean,
  val isAttachedToComposition: Boolean,
  val isDisappearing: Boolean,
  val isBackstackMutation: Boolean,
  val transitions: PortalTransitions,
  val render: PortalRender
) {
  override fun toString() =
    """$name(
      |  key=$key,
      |  wasContentPreviouslyVisible=$wasContentPreviouslyVisible
      |  isAttachedToComposition=$isAttachedToComposition
      |  isDisappearing=$isDisappearing
      |  isBackstackMutation=$isBackstackMutation
      |)""".trimMargin()

  private inline val name get() = this::class.simpleName
}