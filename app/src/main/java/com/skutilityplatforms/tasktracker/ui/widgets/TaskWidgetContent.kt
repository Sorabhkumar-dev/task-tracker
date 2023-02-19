package com.skutilityplatforms.tasktracker.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun TaskWidgetContent(modifier: GlanceModifier) {
    Column(modifier = modifier) {
        Text(
            text = "You have new task to do",
            modifier = GlanceModifier.fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            maxLines = 1
        )

        Text(
            text = "Hey, currently you have new task do",
            modifier = GlanceModifier.fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            maxLines = 3
        )

        Text(
            text = "xx xxx xxxx",
            modifier = GlanceModifier.fillMaxWidth().padding(16.dp),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            maxLines = 1
        )
    }
}