package uptop.me.testcoroutine

import uptop.me.testcoroutine.data.Network

class SomeRepositoryImpl(private val network: Network) {

    fun getData() = network.someApi.getData()


}