import java.util.concurrent.CompletableFuture;

public class Service {

    public void executeServices(){
        //service 1
        CompletableFuture<Void> service1 = CompletableFuture.runAsync(this::callService1);//Запускает Service 1 асинхронно в отдельном потоке с использованием CompletableFuture.runAsync.
        //Метод runAsync запускает переданную задачу (вызов callService1) в ForkJoinPool (дефолтный пул потоков в Java), позволяя выполнять её параллельно с основным потоком.

        //service 2
        CompletableFuture<Void> service2 = service1.thenRunAsync(this::callService2);//После завершения выполнения service1 автоматически запускается service2, который выполняет метод callService2.

        System.out.println("Main Thread: join services");
        CompletableFuture.allOf(service1, service2).join();//дожидается завершения service1 перед продолжением выполнения основного потока.
        System.out.println("Main Thread: all services completed");
    }

    private void callService1(){
        System.out.println("Service 1: Executing...");
        workService(3000);
        System.out.println("Service 1: Completed");
    }

    private void callService2() {
        System.out.println("Service 2: Executing...");
        workService(3000);
        System.out.println("Service 2: Completed.");
    }

    private void workService(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
