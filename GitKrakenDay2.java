import java.util.concurrent.*;

public class GitKrakenDay2 implements Callable<Integer> { //implement callable instead of runnable because runnable does not return an values and cannot accept any parameters; however, callable can, and we make it return an integer

    static int total = 0; //create my integer total that goes to a billion
    static ExecutorService executorService = Executors.newFixedThreadPool(1000); //an executor service is important in managing threads. you can submit tasks and manage a pool of threads (which we use in this case because we have 1000 threads that we want to manage)
    public static void main (String [] args) {
        Future <Integer> future = executorService.submit(new GitKrakenDay2()); //using future, we submit our task that the executor service does into the future. future allows you to track the progress of an asynchronous thread
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
    public Integer call() throws Exception { //my call method that counts to 1,000,000 and returns the int - something that a runnable cannot do. for the previous code, we had to use an array
        int total = 0;
        for (int i = 0; i < 1000000; i++) {
            total++;
        }
        return total;
    }
}
