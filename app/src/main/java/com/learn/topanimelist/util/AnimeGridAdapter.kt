package com.learn.topanimelist.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learn.topanimelist.R
import com.learn.topanimelist.databinding.GridViewItemBinding
import com.learn.topanimelist.model.AnimeModel

class AnimeGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<AnimeModel,
            AnimeGridAdapter.AnimeViewHolder>(DiffCallback) {

    class AnimeViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animeModel: AnimeModel) {
            binding.anime = animeModel
            binding.executePendingBindings() // for recyclerview to exceute quickly
        }
    }

    // to tell recycler view item changed
    companion object DiffCallback : DiffUtil.ItemCallback<AnimeModel>() {
        override fun areItemsTheSame(oldItem: AnimeModel, newItem: AnimeModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AnimeModel, newItem: AnimeModel): Boolean {
            return oldItem.rank == newItem.rank
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimeViewHolder {
        val withDataBinding: GridViewItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.grid_view_item,
                parent,
                false
            )

        return AnimeViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(anime)
        }
        holder.bind(anime)
    }

    // click listener for item in view holder
    class OnClickListener(val clickListener: (animeModel: AnimeModel) -> Unit) {
        fun onClick(animeModel: AnimeModel) = clickListener(animeModel)
    }
}