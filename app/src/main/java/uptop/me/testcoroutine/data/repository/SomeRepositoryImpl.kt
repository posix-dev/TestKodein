package uptop.me.testcoroutine.data.repository

import uptop.me.testcoroutine.data.Network
import uptop.me.testcoroutine.domain.repository.SomeRepository

class SomeRepositoryImpl(private val network: Network) : SomeRepository {

    override suspend fun getData() = network.someApi.getData()


}