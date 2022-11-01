package com.rappi.emovie

import com.rappi.core.data.usecaseimplementations.GetMoviesUseCaseImpl
import com.rappi.core.data.usecaseimplementations.MoviesCheckIfRequireNewPageUSeCaseImpl
import com.rappi.emovie.movies.presentation.viewmodel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.Rule

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Listening to movies Flow emits the list of movies from the server`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val getMoviesUseCase = GetMoviesUseCaseImpl(FakeLocalDataSource())
        val moviesCheckIfRequireNewPageUseCase = MoviesCheckIfRequireNewPageUSeCaseImpl(FakeLocalDataSource(), FakeRemoteDataSource())
        val vm = MoviesViewModel(moviesCheckIfRequireNewPageUseCase, getMoviesUseCase)

        vm.topRatedMovies.collect {
            Assert.assertEquals(fakeMoviesTopRated, it)
        }
    }

}