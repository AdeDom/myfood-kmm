package com.adedom.core.presentation.login.state

data class LoginViewState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginSuccess: Unit? = null,
)