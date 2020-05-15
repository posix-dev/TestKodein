package uptop.me.testcoroutine.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*
import uptop.me.testcoroutine.presentation.ui.base.BaseFragment
import uptop.me.testcoroutine.presentation.ui.event.MainEvents
import uptop.me.testcoroutine.presentation.viewmodel.MainViewModelImpl

class MainFragment : BaseFragment<MainViewModelImpl, MainViewModelImpl.State, Nothing, MainEvents>() {

    override val layoutRes: Int = uptop.me.testcoroutine.R.layout.fragment_main

    override fun provideViewModel(): MainViewModelImpl =
        ViewModelProvider(this, viewModeFactory).get(MainViewModelImpl::class.java)

    override fun render(state: MainViewModelImpl.State) {
        setProgressShowing(state.isLoading)
        tvHello.text = state.data
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(MainEvents.Initial)
    }
}