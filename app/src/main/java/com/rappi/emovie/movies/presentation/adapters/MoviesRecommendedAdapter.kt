package com.test.test.movies.presentation.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rappi.core.BuildConfig.CORE_POSTER_BASE_URL
import com.rappi.core.commons.dpis
import com.rappi.core.domain.models.MoviesModel
import com.rappi.emovie.R
import com.rappi.emovie.databinding.ItemRecommendedMoviesHomeBinding
import com.rappi.emovie.movies.presentation.adapters.DiffCallback
import com.rappi.emovie.movies.presentation.models.MoviesFilterModel

class MoviesRecommendedAdapter(
    context: Context,
    private val onClickCallback: ((item: MoviesModel) -> Unit)?
) :
    ListAdapter<MoviesModel, MoviesRecommendedAdapter.ItemViewholder>(DiffCallback()) {
    private var r: Resources = context.resources
    private var movies: List<MoviesModel> = emptyList()
    private var filters = mutableListOf<MoviesFilterModel>()

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterBaseUrl = CORE_POSTER_BASE_URL

        private val binding = ItemRecommendedMoviesHomeBinding.bind(itemView)

        fun bind(item: MoviesModel, position: Int) = with(binding) {
            Glide
                .with(ivPoster.context)
                .load("$posterBaseUrl${item.posterPath}")
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(12f.dpis(r))))
                .into(ivPoster)
        }

        init {
            itemView.setOnClickListener { onClickCallback?.invoke(getItem(adapterPosition)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder =
        ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recommended_movies_home, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    fun setData(movies: List<MoviesModel>) {
        this.movies = movies
        submitList(movies)
    }

    fun filter(filter: MoviesFilterModel) {
        filters.add(filter)
        updateDataFiltered()
    }

    fun unfilter(filter: MoviesFilterModel) {
        filters.remove(filter)
        updateDataFiltered()
    }

    private fun updateDataFiltered() {
        if (filters.isEmpty()) {
            submitList(movies)
        } else {
            val filteredMovies = movies.filter { movies ->
                filters.any {
                    if (it.isDate) {
                        it.query.take(4) == movies.releaseDate.take(4)
                    } else {
                        it.query == movies.language
                    }
                }
            }
            submitList(filteredMovies)
        }
    }
}