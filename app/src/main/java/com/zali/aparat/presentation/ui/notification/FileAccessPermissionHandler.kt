package com.zali.aparat.presentation.ui.notification

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class FileAccessPermissionHandler(private val fragment: Fragment, private val callback: PermissionCallback) {

    interface PermissionCallback {
        fun onFileAccessPermissionGranted()
        fun onFileAccessPermissionDenied()
    }

    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13 (API level 33) and above, use granular media permissions
            val permissionsToRequest = mutableListOf<String>()
            if (ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.READ_MEDIA_IMAGES)
            }
            if (ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.READ_MEDIA_VIDEO)
                != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.READ_MEDIA_VIDEO)
            }
            if (ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.READ_MEDIA_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.READ_MEDIA_AUDIO)
            }

            if (permissionsToRequest.isNotEmpty()) {
                fragment.requestPermissions(
                    permissionsToRequest.toTypedArray(),
                    REQUEST_MEDIA_PERMISSION
                )
            } else {
                callback.onFileAccessPermissionGranted()
            }
        } else {
            if (ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                fragment.requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_WRITE_STORAGE_PERMISSION
                )
            } else {
                callback.onFileAccessPermissionGranted()
            }
        }
    }

    fun handlePermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_MEDIA_PERMISSION -> {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    callback.onFileAccessPermissionGranted()
                } else {
                    callback.onFileAccessPermissionDenied()
                }
            }
        }
    }

    companion object {
        const val REQUEST_MEDIA_PERMISSION = 1004
        const val REQUEST_WRITE_STORAGE_PERMISSION = 1001

    }
}
