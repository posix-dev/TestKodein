package uptop.me.testcoroutine

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class App : Application(), KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(androidXModule(this@App))
        import(appModule())
    }

    override fun onCreate() {
        super.onCreate()
        kodein
    }

}