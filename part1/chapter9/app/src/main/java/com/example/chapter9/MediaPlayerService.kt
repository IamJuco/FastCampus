package com.example.chapter9

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MediaPlayerService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    // 포그라운드 서비스를 이용 할 거기 때문에 사용하지않음
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    // Service가 시작될 때 처음 불림
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
                stopSelf() // 서비스 중지 메서드 ( 이거 안하면 백그라운드에서 계속 실행이 됌 )
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}