package com.rappi.core.domain.usecaseabstraction

interface MoviesCheckIfRequireNewPageUseCase {
    suspend fun execute(lastVisible: Int, listType: Int)
}