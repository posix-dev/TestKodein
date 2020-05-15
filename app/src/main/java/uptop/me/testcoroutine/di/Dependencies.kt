package uptop.me.testcoroutine.di

import android.content.Context
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import uptop.me.testcoroutine.*
import uptop.me.testcoroutine.data.MFPNetworkImpl
import uptop.me.testcoroutine.data.MFPNetworkStubImpl
import uptop.me.testcoroutine.data.Network
import uptop.me.testcoroutine.data.repository.SomeRepositoryImpl
import uptop.me.testcoroutine.domain.interactor.MainInteractorImpl
import uptop.me.testcoroutine.domain.repository.SomeRepository
import uptop.me.testcoroutine.presentation.viewmodel.MainViewModelImpl

fun appModule(context: Context) = Kodein.Module("appModule") {
    bind<Context>() with provider { context }
    import(dataModule())
    import(domainModule())
}

fun presentationModule() = Kodein.Module("activityModule") {
    bind() from singleton { KodeinViewModelFactory(kodein) }
    bind() from singleton { MainViewModelImpl(instance()) }
}

fun dataModule() = Kodein.Module("dataModule") {
    bind<HttpClientEngine>() with singleton {
        OkHttp.create {
            val networkInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addNetworkInterceptor(networkInterceptor)
        }
    }
    bind<Network>() with singleton {
        getNetworkInjectManager(instance())
    }
    bind<SomeRepository>() with singleton {
        SomeRepositoryImpl(instance())
    }
}

fun domainModule() = Kodein.Module("domainModule") {
    bind<MainInteractorImpl>() with singleton { MainInteractorImpl(instance()) }
}

fun getNetworkInjectManager(clientEngine: HttpClientEngine): Network =
    if (!BuildConfig.DEBUG) {
        MFPNetworkStubImpl()
    } else {
        MFPNetworkImpl(clientEngine)
    }