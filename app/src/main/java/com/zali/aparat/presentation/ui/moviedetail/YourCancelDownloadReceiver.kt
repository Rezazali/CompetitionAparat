package com.zali.aparat.presentation.ui.moviedetail

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.downloader.PRDownloader
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class YourCancelDownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "CANCEL_DOWNLOAD") {
            val downloadId = intent.getIntExtra("downloadId", -1)
            if (downloadId != -1) {
                PRDownloader.cancel(downloadId)

                // Send a local broadcast to inform of the cancellation
                val cancelIntent = Intent("com.zali.aparat.DOWNLOAD_CANCELLED")
                LocalBroadcastManager.getInstance(context).sendBroadcast(cancelIntent)
            }
        }
    }

    companion object {
        const val notificationId = 1001
    }
}


