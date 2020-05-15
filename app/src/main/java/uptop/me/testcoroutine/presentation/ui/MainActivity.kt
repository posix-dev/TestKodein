package uptop.me.testcoroutine.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.kcontext
import uptop.me.testcoroutine.R
import uptop.me.testcoroutine.di.presentationModule


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodeinContext = kcontext<AppCompatActivity>(this)
    private val _parentKodein by closestKodein()
    override val kodein by retainedKodein {
        extend(_parentKodein)
        import(presentationModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kodein.kodeinTrigger?.trigger()

        val newFragment: Fragment = MainFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.mainFrame, newFragment)
        transaction.addToBackStack(null)

// Commit the transaction

// Commit the transaction
        transaction.commit()
//        viewModel.handleEvent()
//        viewModel.doSome { text ->
//            activityMainHello.text = text
//        }
    }

}
