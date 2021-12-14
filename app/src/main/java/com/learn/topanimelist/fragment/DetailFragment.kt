package com.learn.topanimelist.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.learn.topanimelist.R
import com.learn.topanimelist.databinding.FragmentDetailBinding
import com.learn.topanimelist.viewmodels.DetailViewModel
import com.learn.topanimelist.viewmodels.DetailViewModelFactory

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val animeModel = DetailFragmentArgs.fromBundle(requireArguments()).selectedAnime
        val viewModelFactory = DetailViewModelFactory(animeModel, application)
        viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }

}