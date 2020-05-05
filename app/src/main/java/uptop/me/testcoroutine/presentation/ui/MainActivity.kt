package uptop.me.testcoroutine.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.subKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext
import uptop.me.testcoroutine.R
import uptop.me.testcoroutine.SomeRepositoryImpl
import uptop.me.testcoroutine.di.activityModule
import uptop.me.testcoroutine.presentation.viewmodel.MainViewModelImpl

class MainActivity : AppCompatActivity(), KodeinAware {

    private val _parentKodein = closestKodein()
    override val kodeinContext = kcontext<AppCompatActivity>(this)
    override val kodein by subKodein(_parentKodein) {
        import(activityModule())
    }

    private val repositoryImpl by instance<SomeRepositoryImpl>()
    private val viewModeFactory by instance<ViewModelProvider.Factory>()
    private val viewModel: MainViewModelImpl by lazy {
        ViewModelProvider(this, viewModeFactory).get(MainViewModelImpl::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kodein.kodeinTrigger?.trigger()
        repositoryImpl.getData().forEach {
            print("hey $it")
        }
        print("hey ${repositoryImpl.getData()}")
    }

}
