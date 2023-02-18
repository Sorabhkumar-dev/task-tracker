package com.skutilityplatforms.tasktracker.ui.utils

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied() = !status.shouldShowRationale && !status.isGranted
