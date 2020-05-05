package uptop.me.testcoroutine

import uptop.me.testcoroutine.data.network.SomeApi

class SomeApiStub: SomeApi {
    override fun getData() = listOf("1", "2", "3")
}
