package uptop.me.testcoroutine.data

import uptop.me.testcoroutine.data.network.SomeApi
import uptop.me.testcoroutine.SomeApiImpl
import uptop.me.testcoroutine.SomeApiStub

interface Network {
    val someApi: SomeApi
}

class MFPNetworkImpl constructor(
    val interceptors: Int
) : Network {
    override val someApi: SomeApi by lazy { SomeApiImpl() }
}

class MFPNetworkStubImpl constructor(
    val interceptors: Int
) : Network {
    override val someApi: SomeApi by lazy { SomeApiStub() }
}