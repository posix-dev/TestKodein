package uptop.me.testcoroutine

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun appModule() = Kodein.Module("appModule") {
    bind<MainInteractor>() with provider { MainInteractorImpl() }
    bind<MainViewModel>() with provider { MainViewModelImpl(instance()) }
}

//import(appModule())

fun activityModule() = Kodein.Module("activityModule") {
    bind<MainInteractor>() with provider { MainInteractorImpl() }
    bind<MainViewModel>() with provider { MainViewModelImpl(instance()) }
}