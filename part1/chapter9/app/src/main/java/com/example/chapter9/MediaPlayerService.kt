package com.example.chapter9

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.media.MediaPlayer
import android.os.IBinder

class MediaPlayerService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private val receiver = LowBatteryReceiver()

    // 포그라운드 서비스를 이용 할 거기 때문에 사용하지않음
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        initReceiver()

        val playIcon = Icon.createWithResource(baseContext, R.drawable.ic_play)
        val pauseIcon = Icon.createWithResource(baseContext, R.drawable.ic_pause)
        val stopIcon = Icon.createWithResource(baseContext, R.drawable.ic_stop)

        val mainPendingIntent = PendingIntent.getActivity(
            baseContext,
            0,
            Intent(baseContext, MainActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                },
            PendingIntent.FLAG_IMMUTABLE
        )

        val pausePendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, MediaPlayerService::class.java).apply {
                action = MEDIA_PLAYER_PAUSE
            },
            PendingIntent.FLAG_IMMUTABLE
        )

        val playPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, MediaPlayerService::class.java).apply {
                action = MEDIA_PLAYER_PLAY
            },
            PendingIntent.FLAG_IMMUTABLE
        )

        val stopPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, MediaPlayerService::class.java).apply {
                action = MEDIA_PLAYER_STOP
            },
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = Notification.Builder(baseContext, CHANNEL_ID)
            .setStyle(
                Notification.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            // statusBar 알람 아이콘
            .setSmallIcon(R.drawable.ic_star)
            .addAction(
                Notification.Action.Builder(
                    pauseIcon,
                    "Pause",
                    pausePendingIntent
                ).build()
            )

            .addAction(
                Notification.Action.Builder(
                    playIcon,
                    "Play",
                    playPendingIntent
                ).build()
            )

            .addAction(
                Notification.Action.Builder(
                    stopIcon,
                    "Stop",
                    stopPendingIntent
                ).build()
            )
            .setContentIntent(mainPendingIntent)
            .setContentTitle("음악재생")
            .setContentText("음원이 재생 중 입니다...")
            .build()

        // API 34 이상 부터는 ManiFest에 foregroundServiceType을 지정해야함
        // 서비스가 여러가지면 | 연산자로 구분가능
        // ex) android:foregroundServiceType="camera|microphone"
        startForeground(100, notification)
    }

    private fun initReceiver() {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(receiver, filter)
    }

    private fun createNotificationChannel() {
        val channel =
            NotificationChannel(CHANNEL_ID, "MEDIA_PLAYER", NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager = baseContext.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    // onStartCommand = Service가 시작될 때 처음 불림
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            MEDIA_PLAYER_PLAY -> {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(baseContext, R.raw.test)
                }
                mediaPlayer?.start()
            }

            MEDIA_PLAYER_PAUSE -> {
                mediaPlayer?.pause()
            }

            MEDIA_PLAYER_STOP -> {
                mediaPlayer?.stop()
                mediaPlayer?.release() // mediaPlayer 리소스 해제 ( Stop을 해도 리소스는 남아있음 )
                mediaPlayer = null
                stopSelf() // 서비스 중지 메서드 ( 이거 안하면 백그라운드에서 계속 실행이 됌, 알림이 꺼짐 )
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        // 서비스 종료 됐을때 메모리 삭제
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}