import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import ru.fix.dynamic.property.api.DynamicProperty;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class JavaSnipped {

    public static void main(String[] args) {

        CompletableFuture.runAsync(() -> {
            //interesting computation
        });

        ForkJoinPool forkJoinPool;
        forkJoinPool.getQueuedSubmissionCount()

    }


    private static final boolean useCommonPool =
            (ForkJoinPool.getCommonPoolParallelism() > 1);

    private static final Executor asyncPool = useCommonPool ?
            ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();

    static final class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    }

    /**
     * Asynchronously send a record to a topic
     * and invoke the provided callback
     * when the send has been acknowledged.
     */
    Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback) {
        return null;
    }


}

class SadService1 {
    String nonVolatileNonAtomicField = "";

    SadService1(DynamicProperty<String> property) {
        property.addListener((oldValue, newValue) -> {
            nonVolatileNonAtomicField = newValue;
        });
    }
}

class SadService2 {
    volatile Object state;

    SadService2(DynamicProperty<Integer> property) {
        var value = property.get();
        state = calculateState(value);
        property.addListener((oldValue, newValue) -> {
            state = calculateState(newValue);
        });
    }

    private Object calculateState(int value) {
        //...
        return new Object();
    }
}

class SadService3 {
    volatile Object state;

    SadService3(DynamicProperty<Integer> property) {
        property.addListener((oldValue, newValue) -> {
            state = calculateState(newValue);
        });
        state = calculateState(property.get());
    }

    private Object calculateState(int value) {
        //...
        return new Object();
    }
}

class HappyService {
    volatile Object state;

    HappyService(DynamicProperty<Integer> property) {
        property.addAndCallListener((oldValue, newValue) -> {
            state = calculateState(newValue);
        });
    }

    private Object calculateState(int value) {
        //...
        return new Object();
    }
}