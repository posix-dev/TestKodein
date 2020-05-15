package uptop.me.testcoroutine.domain.repository

interface SomeRepository {
    suspend fun getData(): List<String>
}