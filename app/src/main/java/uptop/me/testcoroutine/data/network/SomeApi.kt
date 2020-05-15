package uptop.me.testcoroutine.data.network

interface SomeApi {
    suspend fun getData(): List<String>
}