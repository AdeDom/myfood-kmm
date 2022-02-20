package com.adedom.core.presentation.main.flow

import com.adedom.core.base.BaseFlow
import com.adedom.core.domain.usecase.main.MainUseCase
import com.adedom.core.presentation.main.action.MainViewAction
import com.adedom.core.presentation.main.state.MainViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFlow(
    private val useCase: MainUseCase,
) : BaseFlow<MainViewState, MainViewAction>(MainViewState()) {

    init {
        viewAction
            .onEach { viewAction ->
                when (viewAction) {
                    is MainViewAction.TextChange -> {
                        setState {
                            copy(
                                text = viewAction.text,
                                currentTime = useCase()
                            )
                        }
                    }
                }
            }
            .launchIn(this)
    }

    fun setTextChangeAction(text: String) {
        val action = MainViewAction.TextChange(text)
        setAction(action)
    }
}