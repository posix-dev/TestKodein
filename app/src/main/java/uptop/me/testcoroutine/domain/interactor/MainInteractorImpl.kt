package uptop.me.testcoroutine.domain.interactor

import uptop.me.testcoroutine.domain.repository.SomeRepository

class MainInteractorImpl(private val repository: SomeRepository) {

    suspend fun doIt(): List<String> {
        println("fuck ${javaClass.name}")
        println("fuck ${repository.getData()}")
        return repository.getData()
    }
}