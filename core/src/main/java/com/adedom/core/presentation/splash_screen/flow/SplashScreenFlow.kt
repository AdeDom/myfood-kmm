package com.adedom.core.presentation.splash_screen.flow

import com.adedom.core.base.BaseFlow
import com.adedom.core.presentation.splash_screen.action.SplashScreenViewAction
import com.adedom.core.presentation.splash_screen.state.SplashScreenViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashScreenFlow : BaseFlow<SplashScreenViewState, SplashScreenViewAction>(
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
                }
            }
            .launchIn(this)

        val action = SplashScreenViewAction.Initial
        setAction(action)
    }
}