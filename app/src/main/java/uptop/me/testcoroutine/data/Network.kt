package uptop.me.testcoroutine.data

import io.ktor.client.engine.HttpClientEngine
import uptop.me.testcoroutine.data.network.SomeApi
import uptop.me.testcoroutine.data.network.SomeApiImpl
import uptop.me.testcoroutine.data.network.SomeApiStub

interface Network {
    val someApi: SomeApi
}

class MFPNetworkImpl constructor(
    private val clientEngine: HttpClientEngine
) : Network {
    override val someApi: SomeApi by lazy { SomeApiImpl(clientEngine) }
}

class MFPNetworkStubImpl: Network {
    override val someApi: SomeApi by lazy { SomeApiStub() }
}