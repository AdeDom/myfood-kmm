package com.adedom.core.presentation.splash_screen.flow

import com.adedom.core.base.BaseFlow
import com.adedom.core.data.repositories.Resource
import com.adedom.core.domain.usecase.test_auth.TestAuthUseCase
import com.adedom.core.presentation.splash_screen.action.SplashScreenViewAction
import com.adedom.core.presentation.splash_screen.state.SplashScreenViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashScreenFlow(
    private val testAuthUseCase: TestAuthUseCase,
) : BaseFlow<SplashScreenViewState, SplashScreenViewAction>(
    SplashScreenViewState()
) {

    init {
        viewAction
            .onEach { action ->
                when (action) {
                    SplashScreenViewAction.Initial -> {
                        delay(2_000L)

                        setState {
                            copy(isFinish = true)
                        }
                    }
                    SplashScreenViewAction.OnClickTestAuth -> {
                        callTestAuth()
                    }
                }
            }
            .launchIn(this)

        val action = SplashScreenViewAction.Initial
        setAction(action)
    }

    fun setOnClickTestAuthAction() {
        val action = SplashScreenViewAction.OnClickTestAuth
        setAction(action)
    }

    private suspend fun callTestAuth() {
        val resource = testAuthUseCase()
        when (resource) {
            is Resource.Success -> {
                setState {
                    copy(isFinish = true)
                }
            }
            is Resource.Error -> {
                setError(resource.error)
            }
        }
    }
}