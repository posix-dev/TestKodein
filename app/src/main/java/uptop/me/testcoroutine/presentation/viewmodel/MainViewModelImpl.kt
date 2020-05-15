package uptop.me.testcoroutine.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import uptop.me.testcoroutine.domain.interactor.MainInteractorImpl
import uptop.me.testcoroutine.presentation.ui.base.BaseViewModel
import uptop.me.testcoroutine.presentation.ui.event.MainEvents

class MainViewModelImpl(private val interactor: MainInteractorImpl) :
    BaseViewModel<MainViewModelImpl.State, Nothing, MainEvents>() {

    override fun getInitialViewState(): State = State(isLoading = true)

    override fun handleEvent(uiEvent: MainEvents) = when (uiEvent) {
        MainEvents.Initial -> doSome()
    }

    private fun doSome() {
        var result = ""
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                result = interactor.doIt()[0]
            }
            withContext(Dispatchers.Main) {
                viewState = viewState.copy(isLoading = false, data = result)
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val data: String = ""
    )

//    sealed class EventState {
//        data class Success(val data: String): EventState()
//        data class Error(val error: Throwable) : EventState()
//    }
}