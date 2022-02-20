package com.adedom.core.presentation.login.flow

import com.adedom.core.base.BaseFlow
import com.adedom.core.presentation.login.action.LoginViewAction
import com.adedom.core.presentation.login.state.LoginViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFlow : BaseFlow<LoginViewState, LoginViewAction>(LoginViewState()) {

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

    private suspend fun callLogin() {
        delay(2_000L)
    }
}