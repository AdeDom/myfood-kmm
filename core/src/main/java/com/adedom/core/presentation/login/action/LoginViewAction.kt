package com.adedom.core.presentation.login.action

sealed class LoginViewAction {
    data class UsernameChange(val username: String) : LoginViewAction()
    data class PasswordChange(val password: String) : LoginViewAction()
    object LoginClick : LoginViewAction()
}