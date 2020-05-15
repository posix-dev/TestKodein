package uptop.me.testcoroutine.presentation.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uptop.me.testcoroutine.presentation.viewmodel.news.NewsEvent
import uptop.me.testcoroutine.presentation.viewmodel.news.News

abstract class BaseViewModel<ViewState, News, UiEvent> : ViewModel() {
    private val viewStateLiveData = MutableLiveData<ViewState>()
    private val newsLiveData = NewsEvent<News>()

    protected var viewState: ViewState
        get() = viewStateLiveData.value!!
        set(value) {
            viewStateLiveData.value = value
        }

    @CallSuper
    open fun initialize() {
        if (viewStateLiveData.value == null) {
            viewState = getInitialViewState()
        }
    }

    //send single event
    protected fun sendNews(news: News) = newsLiveData.postValue(News(news))

    protected abstract fun getInitialViewState(): ViewState

    fun getViewStateObservable(): LiveData<ViewState> = viewStateLiveData

    fun getEventsObservable(): NewsEvent<News> = newsLiveData

    abstract fun handleEvent(uiEvent: UiEvent)

}