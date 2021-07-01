package com.unipi.p17172p17168p17164.multiplicationgame.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.unipi.p17172p17168p17164.multiplicationgame.R
import com.unipi.p17172p17168p17164.multiplicationgame.utils.Constants


class BackgroundMusicService : Service() {
    private var player: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        player = MediaPlayer.create(this, R.raw.monkeys_spinning)
        player!!.isLooping = true // Set looping
        player!!.setVolume(Constants.VOLUME_LEFT, Constants.VOLUME_RIGHT)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        if (player?.isPlaying == false)
            player?.start()
    }

    override fun onDestroy() {
        player!!.stop()
        player!!.release()
    }
}