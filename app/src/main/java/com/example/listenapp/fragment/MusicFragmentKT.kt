package com.example.listenapp.fragment


import android.arch.lifecycle.Observer
import android.os.Bundle
import base.FragBind
import com.example.listenapp.custom.recyclerAdapter
import com.example.listenapp.databinding.FragmentMusicBinding
import com.example.listenapp.model.apimodels.Artist
import com.example.listenapp.model.apimodels.Track
import com.example.listenapp.recycler.ItemViewMusic
import com.example.listenapp.viewmodel.ViewModelMusic
import custom.viewBind
import custom.viewModel


class MusicFragmentKT :FragBind<FragmentMusicBinding>(){

    override val binding: FragmentMusicBinding by viewBind()
    private val viewModel by lazy { viewModel<ViewModelMusic>() }

    var trackSet = ArrayList<Track>()
    var pagina = 1
    var totalPaginas = 30

    companion object{
        fun newInstance(bundle: Bundle) = MusicFragmentKT().apply { arguments = bundle }
    }

    override fun FragmentMusicBinding.onBoundView() {
        val adapter = recyclerAdapter<ItemViewMusic>(trackSet)

        adapter.onTarget = {
            while (pagina < totalPaginas) {
                pagina += 1
                viewModel.getTopTracks(pagina)
            }
        }

        recyclerMusic.adapter = adapter

        viewModel.tracks.observe(
                this@MusicFragmentKT,
                Observer { it?.run {
                    trackSet.addAll(it)
                    adapter.notifyDataSetChanged()
                    }
                }
        )

        viewModel.getTopTracks()

    }

    fun viewModelFinder(): ViewModelMusic {
        return viewModel
    }

}