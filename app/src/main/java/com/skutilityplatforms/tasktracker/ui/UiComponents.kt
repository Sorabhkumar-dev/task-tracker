package com.skutilityplatforms.tasktracker.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skutilityplatforms.tasktracker.R
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.ui.utils.Util

@Composable
fun TaskCard(
    modifier: Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    cardElevation: Dp = 12.dp,
    taskInfo: TaskInfo
) {
    Card(modifier = modifier, shape = shape, elevation = cardElevation) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (taskTitle, taskDescription, taskTiming, taskIcon) = createRefs()
            Text(
                text = taskInfo.taskTitle,
                modifier = Modifier.constrainAs(taskTitle) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 16.dp)
                    end.linkTo(taskIcon.start, 10.dp)
                    width = Dimension.fillToConstraints
                }, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.subtitle1
            )

            Text(
                text = taskInfo.taskDescription,
                modifier = Modifier.constrainAs(taskDescription) {
                    start.linkTo(taskTitle.start)
                    top.linkTo(taskTitle.bottom, 8.dp)
                    end.linkTo(taskIcon.start, 10.dp)
                    width = Dimension.fillToConstraints
                }, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.body1
            )

            Text(
                text = Util.getFormattedDate(taskInfo.taskTiming),
                modifier = Modifier.constrainAs(taskTiming) {
                    start.linkTo(taskDescription.start)
                    top.linkTo(taskDescription.bottom, 8.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                    width = Dimension.fillToConstraints
                }, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.body2
            )

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "notification icon",
                modifier = Modifier.constrainAs(taskIcon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.value(40.dp)
                    height = Dimension.value(40.dp)
                }
            )
        }
    }
}

@Composable
fun EmptyScreen(
    modifier: Modifier,
    @StringRes text: Int = R.string.currently_you_have_no_any_task
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search icon",
            modifier = Modifier.size(80.dp)
        )

        Text(
            text = stringResource(id = text),
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}

