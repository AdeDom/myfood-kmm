package com.adedom.core.presentation.main.action

sealed class MainViewAction {
    data class TextChange(val text: String) : MainViewAction()
}