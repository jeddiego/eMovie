package com.rappi.splash.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.usecaseabstraction.SplashGetConfigurationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getConfigurationsUseCase: SplashGetConfigurationsUseCase
): ViewModel() {
    private val _downloadConfigurations = MutableLiveData<Boolean>()
    val downloadConfigurations: LiveData<Boolean> = _downloadConfigurations

    fun downloadConfigurations() {
        viewModelScope.launch(Dispatchers.IO) {
            val state = getConfigurationsUseCase.execute()
            _downloadConfigurations.postValue(state)
        }
    }
}