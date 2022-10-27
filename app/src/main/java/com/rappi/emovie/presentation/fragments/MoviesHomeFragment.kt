package com.rappi.emovie.presentation.fragments
/*
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.rappi.test.R
import com.rappi.test.databinding.FragmentMoviesHomeBinding
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.fragments.BaseFragment
import com.test.test.movies.presentation.adapters.MoviesAdapter
import com.test.test.movies.presentation.adapters.MoviesRecommendedAdapter
import com.test.test.movies.presentation.commons.collectFlow
import com.test.test.movies.presentation.commons.lastVisibleEvents
import com.test.test.movies.presentation.commons.visible
import com.test.test.movies.presentation.models.MoviesFilterModel
import com.test.test.movies.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

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
            val adapter = MoviesRecommendedAdapter(requireContext()) {
                findNavController().navigate(MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMoviesDetailFragment(it))
            }

            lifecycleScope.collectFlow(viewModel.recommendedMovies) {
                adapter.setData(it.take(6))
                showChipFilters(it.take(6))
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
}*/