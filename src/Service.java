import java.util.concurrent.CompletableFuture;

public class Service {

    public void executeServices(){
        //run service 1
        CompletableFuture<Void> service1 = CompletableFuture.runAsync(this::callService1);//Запускает Service 1 асинхронно в отдельном потоке с использованием CompletableFuture.runAsync.
        //Метод runAsync запускает переданную задачу (вызов callService1) в ForkJoinPool (дефолтный пул потоков в Java), позволяя выполнять её параллельно с основным потоком.
        CompletableFuture.allOf(service1).join();//дожидается завершения service1 перед продолжением выполнения основного потока.
        System.out.println("Main Thread: Service 1 is Completed");
    }

    private void callService1(){
        System.out.println("Service 1: Executing...");
        workService(2000);
        System.out.println("Service 1: Completed");
    }

    private void workService(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
