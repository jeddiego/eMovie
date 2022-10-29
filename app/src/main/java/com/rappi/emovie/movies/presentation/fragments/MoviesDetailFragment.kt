package com.rappi.emovie.movies.presentation.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rappi.core.BuildConfig.CORE_POSTER_BASE_URL
import com.rappi.core.commons.visible
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.presentation.fragments.fragments.BaseFragment
import com.rappi.emovie.databinding.FragmentMoviesDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailFragment : BaseFragment<FragmentMoviesDetailBinding>() {
    private val args: MoviesDetailFragmentArgs by navArgs()

    override fun initBinding(): FragmentMoviesDetailBinding =
        FragmentMoviesDetailBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        loadAnimation()
        loadData(args.selectedMovie)
        loadListeners()
    }

    private fun loadAnimation() {
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    private fun loadData(movie: MoviesModel) {
        bind.apply {
            tvTitle.text = movie.name
            chLanguage.text = movie.language
            chReleaseDate.text = movie.releaseDate.take(4)
            chRating.text = movie.voteAverage
            tvOverview.text = movie.overview
            btTrailer.visible = !movie.trailerId.isNullOrBlank()

            Glide
                .with(requireContext())
                .load("${CORE_POSTER_BASE_URL}${movie.posterPath}")
                .into(ivPoster)
        }
    }

    private fun loadListeners() {
        bind.apply {
            ivBack.setOnClickListener { findNavController().navigateUp() }
            btTrailer.setOnClickListener {
                args.selectedMovie.trailerId?.let { movieId: String ->
                    watchYoutubeVideo(
                        movieId
                    )
                }
            }
        }
    }

    private fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            requireContext().startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            requireContext().startActivity(webIntent)
        }
    }
}