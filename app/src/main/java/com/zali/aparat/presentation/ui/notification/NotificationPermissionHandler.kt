package com.zali.aparat.presentation.ui.notification

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class NotificationPermissionHandler(private val fragment: Fragment, private val callback: PermissionCallback) {

    interface PermissionCallback {
        fun onNotificationPermissionGranted()
        fun onNotificationPermissionDenied()
    }

    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                fragment.requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_POST_NOTIFICATION_PERMISSION
                )
            } else {
                callback.onNotificationPermissionGranted()
            }
        } else {
            // For OS versions below Android 13, no need to request this permission
            callback.onNotificationPermissionGranted()
        }
    }

    fun handlePermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_POST_NOTIFICATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callback.onNotificationPermissionGranted()
            } else {
                callback.onNotificationPermissionDenied()
            }
        }
    }

    companion object {
        const val REQUEST_POST_NOTIFICATION_PERMISSION = 1003
    }
}

