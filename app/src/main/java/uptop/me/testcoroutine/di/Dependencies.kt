package uptop.me.testcoroutine.di

import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import uptop.me.testcoroutine.*
import uptop.me.testcoroutine.data.MFPNetworkImpl
import uptop.me.testcoroutine.data.MFPNetworkStubImpl
import uptop.me.testcoroutine.data.Network
import uptop.me.testcoroutine.domain.interactor.MainInteractorImpl
import uptop.me.testcoroutine.presentation.viewmodel.MainViewModelImpl

fun appModule(context: Context) = Kodein.Module("appModule") {
    bind<Context>() with provider { context }
    constant(tag = "stubNumber") with 8
    constant(tag = "productionNumber") with 1
    import(dataModule())
}



fun activityModule() = Kodein.Module("activityModule") {
    bind<MainInteractorImpl>() with provider { MainInteractorImpl() }
    bind() from singleton {
        KodeinViewModelFactory(
            kodein
        )
    }
    bind() from singleton { MainViewModelImpl(instance()) }
}

fun dataModule() = Kodein.Module("dataModule") {
    bind<Network>() with singleton {
        getNetworkInjectManager(instance(tag = "stubNumber"))
    }
    bind<SomeRepositoryImpl>() with singleton { SomeRepositoryImpl(instance()) }
}

fun domainModule() = Kodein.Module("domainModule") {

}

fun getNetworkInjectManager(number: Int): Network =
    if (BuildConfig.DEBUG) {
        MFPNetworkStubImpl(number)
    } else {
        MFPNetworkImpl(number)
    }