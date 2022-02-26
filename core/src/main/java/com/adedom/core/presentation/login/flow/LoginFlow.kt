package com.adedom.core.presentation.login.flow

import com.adedom.core.base.BaseFlow
import com.adedom.core.data.repositories.Resource
import com.adedom.core.domain.usecase.login.LoginUseCase
import com.adedom.core.presentation.login.action.LoginViewAction
import com.adedom.core.presentation.login.state.LoginViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginFlow(
    private val useCase: LoginUseCase,
) : BaseFlow<LoginViewState, LoginViewAction>(LoginViewState()) {

    init {
        viewAction
            .onEach { action ->
                when (action) {
                    is LoginViewAction.UsernameChange -> {
                        setState {
                            copy(username = action.username)
                        }
                    }
                    is LoginViewAction.PasswordChange -> {
                        setState {
                            copy(password = action.password)
                        }
                    }
                    LoginViewAction.LoginClick -> {
                        setState {
                            copy(isLoading = true)
                        }

                        callLogin()

                        setState {
                            copy(isLoading = false)
                        }
                    }
                }
            }
            .launchIn(this)
    }

    fun setUsernameChangeAction(username: String) {
        val action = LoginViewAction.UsernameChange(username)
        setAction(action)
    }

    fun setPasswordChangeAction(password: String) {
        val action = LoginViewAction.PasswordChange(password)
        setAction(action)
    }

    fun setLoginClickAction() {
        val action = LoginViewAction.LoginClick
        setAction(action)
    }

    private fun callLogin() {
        launch {
            val username = viewState.value.username
            val password = viewState.value.password
            val resource = useCase(username, password)
            when (resource) {
                is Resource.Success -> {
                    setState {
                        copy(loginSuccess = resource.data)
                    }
                }
                is Resource.Error -> {
                    setError(resource.error)
                }
            }
        }
    }
}