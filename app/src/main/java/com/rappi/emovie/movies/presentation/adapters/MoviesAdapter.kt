package com.rappi.emovie.movies.presentation.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rappi.core.BuildConfig.CORE_POSTER_BASE_URL
import com.rappi.core.commons.dpis
import com.rappi.core.domain.models.MoviesModel
import com.rappi.emovie.R
import com.rappi.emovie.databinding.ItemMoviesHomeBinding

class MoviesAdapter(
    context: Context,
    private val onClickCallback: ((item: MoviesModel) -> Unit)?
) :
    ListAdapter<MoviesModel, MoviesAdapter.ItemViewholder>(DiffCallback()) {
    private var r: Resources = context.resources

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterBaseUrl = CORE_POSTER_BASE_URL

        private val binding = ItemMoviesHomeBinding.bind(itemView)

        fun bind(item: MoviesModel, position: Int) = with(binding) {

            Glide
                .with(ivPoster.context)
                .load("$posterBaseUrl${item.posterPath}")
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(8f.dpis(r))))
                .into(ivPoster)
        }

        init {
            itemView.setOnClickListener { onClickCallback?.invoke(getItem(adapterPosition)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder =
        ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movies_home, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }
}

class DiffCallback : DiffUtil.ItemCallback<MoviesModel>() {
    override fun areItemsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
        return oldItem == newItem
    }
}