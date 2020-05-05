package uptop.me.testcoroutine.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<ViewModel : BaseViewModel<ViewState, Event>, ViewState, Event> :
    Fragment() {
    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        viewModel.initialize()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getViewStateObservable()
            .observe(viewLifecycleOwner, Observer {
                render(it ?: return@Observer)
            })

//        viewModel.getEventsObservable()
//            .observe(viewLifecycleOwner, Observer {
//                handleEvent(it.getContentIfNotHandled() ?: return@Observer)
//            })
    }


    protected abstract fun provideViewModel(): ViewModel

    protected abstract fun render(state: ViewState)

    protected open fun handleEvent(event: Event) = Unit
}