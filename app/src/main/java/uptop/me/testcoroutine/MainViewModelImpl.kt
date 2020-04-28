package uptop.me.testcoroutine

class MainViewModelImpl(private val interactor: MainInteractor): MainViewModel {

    override fun doSomething() = interactor.doIt()

}