package uptop.me.testcoroutine.domain.handler

interface ErrorHandler: (Throwable, () -> Unit) -> Unit