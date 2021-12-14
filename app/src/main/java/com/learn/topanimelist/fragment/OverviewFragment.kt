package com.learn.topanimelist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.learn.topanimelist.R
import com.learn.topanimelist.databinding.FragmentOverviewBinding
import com.learn.topanimelist.util.AnimeGridAdapter
import com.learn.topanimelist.viewmodels.OverviewViewModel


/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class OverviewFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // add adapter for recycler view
        binding.photosGrid.adapter = AnimeGridAdapter(AnimeGridAdapter.OnClickListener {
            viewModel.displayAnimeDetails(it)
        })

        binding.photosGrid.layoutManager = GridLayoutManager(context,2)

        // navigate to detail fragment
        viewModel.navigateToSelectedAnime.observe(viewLifecycleOwner, Observer {
            if (null != it){
                this.findNavController()
                    .navigate(
                        OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it)
                    )
                viewModel.displayAnimeDetailsDone()
            }
        })


        return binding.root
    }


}