package ru.x5.mfp.utils.mvvm.event

import androidx.lifecycle.Observer
import uptop.me.testcoroutine.presentation.viewmodel.news.News

class EventsObserver<T>(private val onEvent: (T) -> Unit) : Observer<News<T>> {

    override fun onChanged(event: News<T>?) {
        event
            ?.getContentIfNotHandled()
            ?.let(onEvent)
    }
}