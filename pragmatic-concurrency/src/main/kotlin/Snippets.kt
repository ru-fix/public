import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

class MyClass {

    fun doSomethingSmart(a: Int, b: Int): Int {
        val result = CompletableFuture.supplyAsync {
            a + b
        }.thenApply {
            it * 2
        }.get()

        return result * 3
    }


    fun with() {
        data class Payload(val tarificationStatus: String)

        val Success = ""
        val log = object {
            fun error(str: String) = TODO()
        }
        val request = Payload()
        val user = Payload()


        if (request.tarificationStatus == Success) {
            log.error("Tarification failed")
            return false
        }

        if (request.tarificationStatus == Success) {
            log.error("Tarification failed for user $user, request: $request")
            return false
        }

        object NamedExecutors {
            fun newFixedThreadPool(str: String, i: Int) = TODO()
        }

        val pool1 = Executors.newFixedThreadPool(10)

        val pool2 = NamedExecutors.newFixedThreadPool("finance-report-builder", 10)


    }

    interface Limiter {
        fun <T> blockingEnqueue(future: CompletableFuture<T>): CompletableFuture<T>

    }

    fun asyncRequest(data: Int): CompletableFuture<Int> = TODO()
    fun isNeedToShutdown(): Boolean = TODO()
    fun getNextPortionOfDataToProcess(): List<Int> = TODO()
    fun getDataToProcess(): CompletableFuture<Int> = TODO()
    fun doSmartThing(data: Int): Int = TODO()

    fun <T> log(any: Any?):T = TODO()

    fun limit(pendingFutureLimiter: Limiter, response: CompletableFuture<Int>) {


        while (!isNeedToShutdown()) {
            val future = getDataToProcess()
                    .thenComposeAsync { data ->
                        asyncRequest(data)
                    }.thenApplyAsync {
                        doSmartThing(it)
                    }
            pendingFutureLimiter.blockingEnqueue(future)
                    .exceptionally { exc -> log(exc) }

        }
    }

    fun processData{

    }
}


