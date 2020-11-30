package com.example.application

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.track.*

class MusicInfo : Fragment() {

    private var controller: MediaPlayerController? = null
    public interface MediaPlayerController{
        fun start(audio: Music)
        fun stop()
        fun next()
        fun previous()
        fun continuePlay()
    }

    private var currentAudio : Music? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.track, container,false)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val audio = arguments?.getSerializable("audio") as Music
        setAudio(audio)
        previous.setOnClickListener {
            controller?.previous()
        }

        next.setOnClickListener {
            controller?.next()
        }

        start.setOnClickListener {
            controller?.continuePlay()
            start.visibility = View.INVISIBLE
            stop.visibility = View.VISIBLE
        }

        stop.setOnClickListener {
            controller?.stop()
            stop.visibility = View.INVISIBLE
            start.visibility = View.VISIBLE
        }

    }

    fun setAudio(audio: Music){
        currentAudio = audio
        author.text = audio.author
        music_title.text = audio.name
        image.setImageResource(audio.icon)
        controller?.start(audio)
        start.visibility = View.INVISIBLE
        stop.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(audio: Music): MusicInfo {
            val args = Bundle()
            args.putSerializable("audio", audio)
            val fragment = MusicInfo()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller = context as? MediaPlayerController
    }

}