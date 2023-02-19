package com.skutilityplatforms.tasktracker.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.padding

class TaskWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        TaskWidgetContent(
            modifier = GlanceModifier
                .background(Color.White)
                .appWidgetBackground()
                .cornerRadius(16.dp)
                .padding(8.dp)
        )
    }
}