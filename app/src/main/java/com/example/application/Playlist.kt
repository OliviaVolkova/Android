package com.example.application

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.list.*

class Playlist : Fragment() {

    private var startListener : StartListener? = null

    public interface StartListener{
        fun touched(audio: Music)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        music_recycler.adapter =
            MusicAdapter(Musics.getAllMusic()) {
                startListener?.touched(it)
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startListener = context as? StartListener
    }


    companion object{
        fun newInstance(): Playlist {
            val args = Bundle()
            val fragment = Playlist()
            fragment.arguments = args
            return fragment
        }
    }
}