package uptop.me.testcoroutine

class MainInteractorImpl : MainInteractor {
    override fun doIt() {
        println("fuck ${javaClass.name}")
    }
}