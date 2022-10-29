package com.rappi.emovie.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.data.utils.LIST_TYPE_TOP_RATED
import com.rappi.core.data.utils.LIST_TYPE_UPCOMING
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.domain.usecaseabstraction.GetMoviesUseCase
import com.rappi.core.domain.usecaseabstraction.MoviesCheckIfRequireNewPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesCheckIfRequireNewPageUseCase: MoviesCheckIfRequireNewPageUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {
    private val _upcomingSpinner = MutableStateFlow(true)
    val upcomingSpinner: StateFlow<Boolean> get() = _upcomingSpinner

    private val _topRatedSpinner = MutableStateFlow(true)
    val topRatedSpinner: StateFlow<Boolean> get() = _topRatedSpinner

    val upcomingMovies: Flow<List<MoviesModel>>
        get() = getMoviesUseCase.getMoviesByListType(
            LIST_TYPE_UPCOMING
        )
    val topRatedMovies: Flow<List<MoviesModel>>
        get() = getMoviesUseCase.getMoviesByListType(
            LIST_TYPE_TOP_RATED
        )

    val recommendedMovies: Flow<List<MoviesModel>>
        get() = getMoviesUseCase.getRecommendedMovies()

    init {
        viewModelScope.launch {
            notifyUpcomingLastVisible(0)
            notifyTopRatedLastVisible(0)
        }
    }

    suspend fun notifyUpcomingLastVisible(lastVisible: Int) {
        moviesCheckIfRequireNewPageUseCase.execute(lastVisible, LIST_TYPE_UPCOMING)
        _upcomingSpinner.value = false
    }

    suspend fun notifyTopRatedLastVisible(lastVisible: Int) {
        moviesCheckIfRequireNewPageUseCase.execute(lastVisible, LIST_TYPE_TOP_RATED)
        _topRatedSpinner.value = false
    }
}