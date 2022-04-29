package decrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DecryptLauncher {

    public void loadData() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "message.txt");
        int[] numbers = Arrays.stream(Files.readString(path).split(",")).mapToInt(Integer::parseInt).toArray();

        DecyptWorker.setNumbers(numbers);
    }

    public void runTasks() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<DecyptWorker> worker = new ArrayList<>();
        for(int c = 'a'; c<= 'z'; c++) {
            worker.add(new DecyptWorker((char)c));
        }

        try {
            String result = pool.invokeAny(worker);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

    public DecryptLauncher() {
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DecryptLauncher launcher = new DecryptLauncher();
        launcher.runTasks();

    }

}
