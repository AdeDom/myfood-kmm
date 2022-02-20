package com.adedom.core.base

import com.adedom.core.data.models.response.base.BaseError
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

abstract class BaseFlow<S : Any, A : Any>(initialUiState: S) : CoroutineScope {

    private val job = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()
        val messageString = error.message ?: "Error"
        val baseError = BaseError(message = messageString)
        setError(baseError)
    }

    private val _viewState = MutableStateFlow(initialUiState)
    val viewState: StateFlow<S> = _viewState.asStateFlow()

    private val _viewAction = MutableSharedFlow<A>()
    val viewAction: SharedFlow<A> = _viewAction.asSharedFlow()

    private val _error = MutableStateFlow<BaseError?>(null)
    val error: StateFlow<BaseError?> = _error

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + exceptionHandler

    fun onCleared() {
        coroutineContext.cancel()
    }

    /**
     * Given a currentState [reducer] and some action [setState] that the user took, produce a new [viewState].
     * This will give us clear and predictable state management, that ensures each state is associated
     * with some specific user event or action.
     * This [reducer] is responsible for handling any Action, and using that to create a new [viewState].
     */
    protected fun setState(reducer: S.() -> S) {
        _viewState.update {
            viewState.value.reducer()
        }
    }

    protected fun setAction(action: A) {
        launch {
            _viewAction.emit(action)
        }
    }

    protected fun setError(error: BaseError) {
        _error.value = error
    }
}