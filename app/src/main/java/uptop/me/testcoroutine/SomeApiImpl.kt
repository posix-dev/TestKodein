package uptop.me.testcoroutine

import uptop.me.testcoroutine.data.network.SomeApi

class SomeApiImpl: SomeApi {
    override fun getData(): List<String> = listOf("3", "2", "1")
}