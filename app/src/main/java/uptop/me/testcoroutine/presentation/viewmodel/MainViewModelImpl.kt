package uptop.me.testcoroutine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import uptop.me.testcoroutine.domain.interactor.MainInteractorImpl

class MainViewModelImpl(private val interactor: MainInteractorImpl) : ViewModel() {

    var state: State = State(eventState = State.EventState.LoadingState)

    fun doSome() {
        state = state.copy(eventState = State.EventState.ErrorState(
            error = NullPointerException()
        ))
    }

    data class State(val eventState: EventState) {
        sealed class EventState {
            object LoadingState : EventState()
            data class DataState(val data: String)
            data class ErrorState(val error: Throwable) : EventState()
        }
    }
}