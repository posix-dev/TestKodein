package uptop.me.testcoroutine.presentation.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState, Event> : ViewModel() {
    private val onNextStub: (Any) -> Unit = { Unit }
    private val onCompleteStub: () -> Unit = { Unit }

    private val viewStateLiveData = MutableLiveData<ViewState>()
//    private val eventsLiveData = SingleLiveEvent<Event>()

    protected var viewState: ViewState
        get() = viewStateLiveData.value!!
        set(value) {
            viewStateLiveData.value = value
        }

//    protected val disposables = CompositeDisposable()

    @CallSuper
    open fun initialize() {
        if (viewStateLiveData.value == null) {
            viewState = getInitialViewState()
        }
    }

    protected abstract fun getInitialViewState(): ViewState

    protected fun sendEvent(event: Event) {
//        eventsLiveData.postValue(LiveEvent(event))
    }

    fun getViewStateObservable(): LiveData<ViewState> = viewStateLiveData

//    fun getEventsObservable(): SingleLiveEvent<Event> = eventsLiveData

    override fun onCleared() {
        super.onCleared()
//        disposables.clear()
    }
}