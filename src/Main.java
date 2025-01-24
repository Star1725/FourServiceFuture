public class Main {
    public static void main(String[] args) {
        System.out.println("Run Main Thread");
        Service service = new Service();
        service.executeServices();
    }
}