package uptop.me.testcoroutine.data.network

class SomeApiStub: SomeApi {
    override suspend fun getData() = listOf("1", "2", "3")
}
