import java.util.concurrent.*;

public class GitKrakenDay2 implements Callable<Integer> {

    static int total = 0;
    static ExecutorService executorService = Executors.newFixedThreadPool(1000);
    public static void main (String [] args) {
        Future <Integer> future = executorService.submit(new GitKrakenDay2());
        for (int i = 0; i < 1000; i++) {
            try {
                total += future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("the total is: " + total);
    }

    @Override
    public Integer call() throws Exception {
        int total = 0;
        for (int i = 0; i < 1000000; i++) {
            total++;
        }
        return total;
    }
}
