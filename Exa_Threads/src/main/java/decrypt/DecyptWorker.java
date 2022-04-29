package decrypt;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class DecyptWorker implements Callable<String> {

    private static int[] numbers;
    private static final String[] commonWords = {"and", "then", "the", "from", "end", "that", "with", "you", " as ", " a ", " i ", "for"};

    private char c1;

    public DecyptWorker(char c1) {
        this.c1 = c1;
    }

    public static int[] getNumbers() {
        return numbers;
    }

    public static void setNumbers(int[] numbers) {
        DecyptWorker.numbers = numbers;
    }

    @Override
    public String call() throws Exception {

        for(char c2 = 'a'; c2 <= 'z'; c2++){
            for(char c3 = 'a'; c3 <= 'z'; c3++){
                StringBuffer text = new StringBuffer(numbers.length);
                for (int i = 0; i < numbers.length; i++) {
                    int key = i % 3 == 0 ? c1 : i % 3 == 1 ? c2 : c3;
                    text.append((char)(numbers[i] ^ key));
                }
                if(Arrays.stream(commonWords).filter(w -> text.toString().toLowerCase().contains(w)).count() > 3) {
                    System.out.println(""+c1+c2+c3);
                    return text.toString();
                }
            }
        }

        throw new Exception("not found");
    }
}
