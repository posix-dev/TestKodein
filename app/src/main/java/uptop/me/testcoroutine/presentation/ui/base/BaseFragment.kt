package uptop.me.testcoroutine.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import uptop.me.testcoroutine.BuildConfig
import uptop.me.testcoroutine.uikit.dialog.ProgressDialog

abstract class BaseFragment<ViewModel : BaseViewModel<ViewState, News, UiEvent>, ViewState, News, UiEvent> :
    Fragment(), KodeinAware {
    @get:LayoutRes
    protected abstract val layoutRes: Int

    private val progress by lazy { ProgressDialog(requireContext()) }

    override val kodein: Kodein by kodein()

    final override val kodeinTrigger: KodeinTrigger?
        get() = if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger

    protected val viewModeFactory by instance<ViewModelProvider.Factory>()

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
        kodeinTrigger?.trigger()

        viewModel.getViewStateObservable()
            .observe(viewLifecycleOwner, Observer {
                render(it ?: return@Observer)
            })

        viewModel.getEventsObservable()
            .observe(viewLifecycleOwner, Observer {
                handleNews(it.getContentIfNotHandled() ?: return@Observer)
            })
    }

    protected fun setProgressShowing(isShowing: Boolean) {
        progress.isShowing = isShowing
    }

    protected fun showProgressNow() {
        progress.showNow()
    }

    fun getProgressDialog(): ProgressDialog = progress

    override fun onStop() {
        progress.dismissNow()
        super.onStop()
    }

    protected abstract fun provideViewModel(): ViewModel

    protected abstract fun render(state: ViewState)

    protected open fun handleNews(news: News) = Unit
}