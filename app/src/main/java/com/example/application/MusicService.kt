package com.example.application

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicService : Service() {

    private var connectedActivity : AudioControl? = null
    private val idNotivication: Int = 14212

    interface AudioControl{
        fun setAudio(audio: Music)
    }

    private var mediaPlayer: MediaPlayer? = null
    private var currentMusic: Music? = null


    private lateinit var localBinder: LocalBinder

    inner class LocalBinder : Binder(){

        fun setCurrentActivity(activity: AudioControl){
            connectedActivity = activity
        }

        fun clearCurrentActivity(){
            connectedActivity = null
        }

        fun startPlayAudio(audio: Music) {
            startPlay(audio)
        }

        fun pausePlayMusic(){
            pausePlay()
        }

        fun continuePlayAudio(){
            continuePlay()
        }

        fun nextPlayAudio(){
            nextPlay()
        }

        fun previousPlayAudio(){
            previousPlay()
        }

    }

    override fun onCreate() {
        super.onCreate()
        localBinder = LocalBinder()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "STOP" -> pausePlay()
            "START" -> continuePlay()
            "NEXT" -> nextPlay()
            "PREVIOUS"->previousPlay()
        }
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(intent: Intent): IBinder = localBinder

    private fun startPlay(music: Music){
        currentMusic = music
        mediaPlayer?.stop()
        mediaPlayer = MediaPlayer.create(this, music.recourse)
        mediaPlayer?.start()
        val notification = Notifications.startNotification(this,music, true)
        startForeground(idNotivication, notification)
    }

    private fun continuePlay(){
        mediaPlayer?.start()
        currentMusic?.let {
            Notifications.startNotification(this, it,true)
        }
    }



    private fun pausePlay(){
        mediaPlayer?.pause()
        currentMusic?.let {
            Notifications.startNotification(this,it,false)
        }
    }

    private fun nextPlay(){
        currentMusic?.let {
            val audio = Musics.nextMusic(it.id)
            audio?.let {audioS->
                connectedActivity?.setAudio(audioS)
                startPlay(audioS)
            }
        }
    }


    private fun previousPlay(){
        currentMusic?.let {
            val audio = Musics.previousMusic(it.id)
            audio?.let {audioS->
                connectedActivity?.setAudio(audioS)
                startPlay(audioS)
            }
        }
    }
}