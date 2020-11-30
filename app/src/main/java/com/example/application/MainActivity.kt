package com.example.application

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder

class MainActivity : AppCompatActivity(), Playlist.StartListener,MusicInfo.MediaPlayerController, MusicService.AudioControl {


    private var binder: MusicService.LocalBinder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this,MusicService::class.java))
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, Playlist.newInstance())
            .addToBackStack(null).commit()
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = service as? MusicService.LocalBinder
            binder?.setCurrentActivity(this@MainActivity)
        }
        override fun onServiceDisconnected(name: ComponentName?) {
           binder = null
        }
    }

    override fun onStart() {
        super.onStart()
        val myIntent = Intent(this, MusicService::class.java)
        bindService(myIntent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        binder?.let{
            it.clearCurrentActivity()
            unbindService(mConnection)
            binder = null
        }
    }

    private var trackFragment: MusicInfo? = null
    override fun touched(audio: Music) {
        trackFragment = MusicInfo.newInstance(audio)
        trackFragment?.let {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, it)
                    .commit()
        }
    }

    override fun start(audio : Music){
        binder?.startPlayAudio(audio)
    }


    override fun stop() {
        binder?.pausePlayMusic()
    }

    override fun next() {
        binder?.nextPlayAudio()
    }

    override fun previous() {
        binder?.previousPlayAudio()
    }

    override fun continuePlay() {
        binder?.continuePlayAudio()
    }

    override fun setAudio(audio: Music) {
        trackFragment?.setAudio(audio)
    }


}