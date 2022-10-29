package com.rappi.emovie.movies.presentation.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.rappi.core.commons.collectFlow
import com.rappi.core.commons.lastVisibleEvents
import com.rappi.core.commons.visible
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.presentation.fragments.fragments.BaseFragment
import com.rappi.emovie.R
import com.rappi.emovie.databinding.FragmentMoviesHomeBinding
import com.rappi.emovie.movies.presentation.adapters.MoviesAdapter
import com.rappi.emovie.movies.presentation.models.MoviesFilterModel
import com.rappi.emovie.movies.presentation.viewmodel.MoviesViewModel
import com.test.test.movies.presentation.adapters.MoviesRecommendedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesHomeFragment : BaseFragment<FragmentMoviesHomeBinding>() {
    private val viewModel: MoviesViewModel by viewModels()

    override fun initBinding(): FragmentMoviesHomeBinding =
        FragmentMoviesHomeBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        loadAnimations()
        loadUpcomingMovies()
        lifecycleScope.launch {
            delay(350)

            loadTopRatedMovies()
            loadRecommendedMovies()
        }
    }

    private fun loadAnimations() {
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadUpcomingMovies() {
        bind.apply {
            val adapter = MoviesAdapter(requireContext()) {
                findNavController().navigate(MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMoviesDetailFragment(it))
            }

            lifecycleScope.collectFlow(viewModel.upcomingSpinner) { pbUpcoming.visible = it }
            lifecycleScope.collectFlow(viewModel.upcomingMovies) {
                adapter.submitList(it)
            }
            lifecycleScope.collectFlow(rvUpcoming.lastVisibleEvents) {
                viewModel.notifyUpcomingLastVisible(it)
            }

            rvUpcoming.adapter = adapter
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadTopRatedMovies() {
        bind.apply {
            val adapter = MoviesAdapter(requireContext()) {
                findNavController().navigate(MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMoviesDetailFragment(it))
            }

            lifecycleScope.collectFlow(viewModel.topRatedSpinner) { pbTopRated.visible = it }
            lifecycleScope.collectFlow(viewModel.topRatedMovies) {
                adapter.submitList(it)
            }
            lifecycleScope.collectFlow(rvTopRated.lastVisibleEvents) {
                viewModel.notifyTopRatedLastVisible(it)
            }

            rvTopRated.adapter = adapter
        }
    }

    private fun loadRecommendedMovies() {
        bind.apply {
            var countRecommended = 0
            val adapter = MoviesRecommendedAdapter(requireContext()) {
                findNavController().navigate(MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMoviesDetailFragment(it))
            }

            lifecycleScope.collectFlow(viewModel.recommendedMovies) {
                if(countRecommended == 0) {
                    adapter.setData(it.take(6))
                    showChipFilters(it.take(6))
                    countRecommended = 6
                }
            }

            rvRecommended.adapter = adapter
        }
    }

    private fun showChipFilters(moviesModels: List<MoviesModel>) {
        val isDate = true

        moviesModels
            .distinctBy { it.language }
            .forEach { addDynamicChip(it.language) }

        moviesModels
            .sortedBy { it.releaseDate }
            .distinctBy { it.releaseDate }
            .forEach { addDynamicChip(it.releaseDate, isDate) }
    }

    private fun addDynamicChip(text: String, isDate: Boolean = false) {
        val chip = LayoutInflater.from(requireContext())
            .inflate(R.layout.item_recommended_movies_filter_chip, null, false) as Chip

        chip.text = if (isDate) {
            getString(R.string.release_date_filter, text.take(4))
        } else {
            text
        }
        bind.cgFilters.addView(chip)

        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                (bind.rvRecommended.adapter as MoviesRecommendedAdapter).filter(
                    MoviesFilterModel(text, isDate)
                )
            } else {
                (bind.rvRecommended.adapter as MoviesRecommendedAdapter).unfilter(
                    MoviesFilterModel(text, isDate)
                )
            }
        }
    }
}