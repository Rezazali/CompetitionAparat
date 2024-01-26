package com.zali.aparat.presentation.ui.moviedetail

import android.Manifest
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.zali.aparat.R
import com.zali.aparat.databinding.FragmentMovieDetailBinding
import com.zali.aparat.domain.models.BaseMovie
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.Player
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zali.aparat.presentation.ui.moviedetail.listbottom.BottomSheetAdapter
import com.zali.aparat.presentation.ui.moviedetail.listbottom.Interaction
import com.zali.aparat.presentation.ui.notification.FileAccessPermissionHandler
import com.zali.aparat.presentation.ui.notification.NotificationPermissionHandler
import es.dmoral.toasty.Toasty
import java.io.File


class MovieDetailFragment : Fragment(), Interaction,
    FileAccessPermissionHandler.PermissionCallback,
    NotificationPermissionHandler.PermissionCallback {

    private val cancelReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "com.zali.aparat.DOWNLOAD_CANCELLED") {
                sharedViewModel.isDownloadCanceled.value = true
            }
        }
    }
    private var lastNotificationProgress = -1

    private val minUpdateInterval = 5

    private var notificationBuilder: NotificationCompat.Builder? = null

    private lateinit var fileAccessPermissionHandler: FileAccessPermissionHandler
    private lateinit var notificationPermissionHandler: NotificationPermissionHandler

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var notificationManager: NotificationManagerCompat
    private val notificationId = 1001
    private val channelId = "download_channel"
    private var downloadId: Int = 0

    private val TAG = "MovieDetailFragment"
    private lateinit var simpleExoPlayer: ExoPlayer
    private lateinit var fullScreen: AppCompatImageView

    private var isFullScreen = false


    private var playbackPosition: Long = 0
    private var playWhenReady: Boolean = true

    private lateinit var owner: LifecycleOwner
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private lateinit var binding: FragmentMovieDetailBinding

    lateinit var dialogBottomSheet : BottomSheetDialog

    lateinit var baseMovie: BaseMovie
    lateinit var dirPath : File

    lateinit var itemDownload : ConstraintLayout

    lateinit var bottomSheetAdapter : BottomSheetAdapter

    lateinit var recyclerBottomSheetLink : RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        fileAccessPermissionHandler = FileAccessPermissionHandler(this, this)
        notificationPermissionHandler = NotificationPermissionHandler(this, this)

        savedInstanceState?.let {
            playbackPosition = it.getLong("playbackPosition", 0)
            playWhenReady = it.getBoolean("playWhenReady", true)
        }
        movieDetailViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        sharedViewModel.isDownloadCanceled.observe(this, Observer { isCanceled ->
            if (isCanceled) {
                notificationManager.cancel(notificationId)
            }
        })

        // Initialize NotificationManager
        notificationManager = NotificationManagerCompat.from(requireContext())
        setupNotificationChannel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        dirPath = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "YourAppName")
        if (!dirPath.exists()) {
            dirPath.mkdirs() // Create the directory if it doesn't exist
        }


        initializePlayer()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fullScreen = view.findViewById(R.id.scaling)!!

        fullScreen.setOnClickListener {
            if (!isFullScreen) {
                applyFullScreenLayout()
                isFullScreen = true
            } else {
                isFullScreen = false
                applyDefaultScreenLayout()
            }
        }

        savedInstanceState?.getBoolean("isFullScreen")?.let {
            if (it) applyFullScreenLayout()
        }
        binding.imgMore.setOnClickListener {
            showBottomSheet()
        }
    }


    private fun requestFileAccess() {
        fileAccessPermissionHandler.requestPermission()
    }

    private fun requestNotificationPermission() {
        notificationPermissionHandler.requestPermission()
    }


    private fun showBottomSheet() {

        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
        dialogBottomSheet = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
        dialogBottomSheet.setContentView(view)
        dialogBottomSheet.show()

        itemDownload = view.findViewById(R.id.item_tow)!!

        itemDownload.setOnClickListener {
            dialogBottomSheet.cancel()
            requestFileAccess()
        }

    }

    private fun applyFullScreenLayout() {
        // Hide the NestedScrollView
        binding.nested.visibility = View.GONE

        // Set PlayerView layout parameters to match parent
        val layoutParams = binding.exoPlayer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.height = ConstraintLayout.LayoutParams.MATCH_PARENT
        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        binding.exoPlayer.requestLayout()

        // Set full-screen flags and hide system UI
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    private fun applyDefaultScreenLayout() {
        binding.nested.visibility = View.VISIBLE

        val pixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            250f,
            resources.displayMetrics
        ).toInt()

        val layoutParams = binding.exoPlayer.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
        layoutParams.height = pixels
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        binding.exoPlayer.layoutParams = layoutParams
        binding.exoPlayer.requestLayout()

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(cancelReceiver, IntentFilter("com.zali.aparat.DOWNLOAD_CANCELLED"))
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.stop()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(cancelReceiver)

    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        if (this::simpleExoPlayer.isInitialized) {
            playbackPosition = simpleExoPlayer.currentPosition
            playWhenReady = simpleExoPlayer.playWhenReady
            simpleExoPlayer.pause()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isFullScreen", isFullScreen)
        outState.putLong("playbackPosition", playbackPosition)
        outState.putBoolean("playWhenReady", playWhenReady)
    }

    private fun startDownload(url : String) {

        sharedViewModel.isDownloadCanceled.value = false
        lastNotificationProgress = -1
        val downloadPath = dirPath.absolutePath

        downloadId = PRDownloader.download(url, downloadPath, "downloadedFile.mp4")
            .build()
            .setOnProgressListener { progress ->
                if (!sharedViewModel.isDownloadCanceled.value!!) {
                    val per = (progress.currentBytes * 100 / progress.totalBytes).toInt()
                    showNotification(per)
                }
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    // Handle completion
                    Log.d(TAG, "onDownloadComplete: ")
                }

                override fun onError(error: Error?) {
                    // Handle error
                    Log.d(TAG, "onError: ")
                }
            })

    }

    private fun showNotification(progress: Int) {
        if (!sharedViewModel.isDownloadCanceled.value!!) {
            if (notificationBuilder == null) {
                val cancelIntent = Intent(context, YourCancelDownloadReceiver::class.java).apply {
                    action = "CANCEL_DOWNLOAD"
                    putExtra("downloadId", downloadId)
                }
                val pendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent, PendingIntent.FLAG_IMMUTABLE)

                notificationBuilder = NotificationCompat.Builder(requireContext(), channelId)
                    .setContentTitle("Download")
                    .setSmallIcon(R.drawable.baseline_file_download_24)
                    .addAction(R.drawable.baseline_cancel_24, "Cancel", pendingIntent)
                    .setSound(null) // Ensure no sound is set
                    .setOnlyAlertOnce(true) // Ensure // Disable sound
            }

            if (progress == 100 || progress == 0 || (progress - lastNotificationProgress >= minUpdateInterval)) {
                notificationBuilder?.setContentText("Downloading: $progress%")
                notificationBuilder?.setProgress(100, progress, false)

                val notification = notificationBuilder?.build()
                if (notification != null) {
                    notificationManager.notify(notificationId, notification)
                    lastNotificationProgress = progress
                }
            }
        }
    }


    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Download Channel"
            val descriptionText = "Used for showing download progress"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            notificationManager = NotificationManagerCompat.from(requireContext())
            notificationManager.createNotificationChannel(channel)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fileAccessPermissionHandler.handlePermissionsResult(requestCode, permissions, grantResults)
        notificationPermissionHandler.handlePermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initializePlayer() {
        val receivedString = arguments?.getString("data")

        if (!this::simpleExoPlayer.isInitialized) {
            simpleExoPlayer = ExoPlayer.Builder(requireContext())
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build()
            binding.exoPlayer.player = simpleExoPlayer
        }

        if (receivedString != null) {
            movieDetailViewModel.getAllVideo(receivedString).observe(owner) { t ->
                baseMovie = t

                val mediaSourceFactory = DefaultMediaSourceFactory(requireContext())
                val hlsMediaSource =
                    mediaSourceFactory.createMediaSource(MediaItem.fromUri(Uri.parse(baseMovie.data.attributes.hls_link)))
                simpleExoPlayer.setMediaSource(hlsMediaSource)
                simpleExoPlayer.prepare()


                simpleExoPlayer.seekTo(playbackPosition)
                simpleExoPlayer.playWhenReady = playWhenReady


                bottomSheetAdapter = BottomSheetAdapter(this)
                bottomSheetAdapter.setDataBottomSheet(baseMovie.data.attributes.file_link_all)
                setData(baseMovie)

            }
        }
    }

    private fun setData(baseMovie: BaseMovie) {

        binding.txtTitle.text = baseMovie.data.attributes.title
        binding.bigTitle.text = baseMovie.included[0].attributes.displayName
        binding.seenCount.text = baseMovie.data.attributes.visit_cnt


        Glide.with(requireContext())
            .load(baseMovie.included[0].attributes.avatar)
            .into(binding.imageCircle)

    }

    override fun onItemSelected(url: String) {
        dialogBottomSheet.cancel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotificationPermission()
        }
        startDownload(url)
    }

    override fun onFileAccessPermissionGranted() {
        if (baseMovie.data.attributes.file_link_all[0].urls[0] != ""){

            val view = layoutInflater.inflate(R.layout.bottom_sheet_link, null)
            dialogBottomSheet = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
            dialogBottomSheet.setContentView(view)

            recyclerBottomSheetLink = view.findViewById(R.id.recycler_bottom_sheet)
            recyclerBottomSheetLink.adapter = bottomSheetAdapter
            recyclerBottomSheetLink.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            dialogBottomSheet.show()
        }
    }

    override fun onFileAccessPermissionDenied() {
        Toasty.error(requireContext(), "دسترسی فایل برای دانلود ویدیو لازم است", Toast.LENGTH_SHORT, true).show();
    }

    override fun onNotificationPermissionGranted() {
    }

    override fun onNotificationPermissionDenied() {
        Toasty.error(requireContext(), "دسترسی نوتیفیکیشن برای نشان دادن روند دانلود لازم است", Toast.LENGTH_SHORT, true).show();
    }
}

