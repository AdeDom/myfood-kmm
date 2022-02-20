package com.adedom.core.domain.usecase.main

import com.adedom.core.data.repositories.MainRepository

class MainUseCase(
    private val repository: MainRepository,
) {

    operator fun invoke(): Long {
        return repository.getMainData()
    }
}