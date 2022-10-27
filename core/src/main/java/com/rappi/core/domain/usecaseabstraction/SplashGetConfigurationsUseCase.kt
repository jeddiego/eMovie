package com.rappi.core.domain.usecaseabstraction

interface SplashGetConfigurationsUseCase {
    suspend fun execute(): Boolean
}