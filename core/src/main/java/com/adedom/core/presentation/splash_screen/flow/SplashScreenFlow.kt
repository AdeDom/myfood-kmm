package com.adedom.core.presentation.splash_screen.flow

import com.adedom.core.base.BaseFlow
import com.adedom.core.data.repositories.Resource
import com.adedom.core.domain.usecase.test_auth.TestAuthUseCase
import com.adedom.core.domain.usecase.test_db.TestDatabaseUseCase
import com.adedom.core.presentation.splash_screen.action.SplashScreenViewAction
import com.adedom.core.presentation.splash_screen.state.SplashScreenViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashScreenFlow(
    private val testAuthUseCase: TestAuthUseCase,
    private val testDatabaseUseCase: TestDatabaseUseCase,
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
                    SplashScreenViewAction.OnClickInsertMyFood -> {
                        insertMyFood()
                    }
                    SplashScreenViewAction.OnClickDeleteMyFood -> {
                        deleteMyFood()
                    }
                }
            }
            .launchIn(this)

        testDatabaseUseCase.getMyFoodAll()
            .onEach {
                setState {
                    copy(myFoods = it)
                }
            }
            .launchIn(this)
    }

    fun setOnClickTestAuthAction() {
        val action = SplashScreenViewAction.OnClickTestAuth
        setAction(action)
    }

    fun setOnClickInsertMyFoodAction() {
        val action = SplashScreenViewAction.OnClickInsertMyFood
        setAction(action)
    }

    private suspend fun insertMyFood() {
        testDatabaseUseCase.insertMyFood()
    }

    fun setOnClickDeleteMyFoodAction() {
        val action = SplashScreenViewAction.OnClickDeleteMyFood
        setAction(action)
    }

    private suspend fun deleteMyFood() {
        testDatabaseUseCase.deleteMyFoodAll()
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