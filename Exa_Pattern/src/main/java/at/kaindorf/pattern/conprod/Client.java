package at.kaindorf.pattern.conprod;

import java.util.LinkedList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        Producer prod = new Producer(list);
        Consumer cons = new Consumer(list);

        Thread threadProd = new Thread(prod);
        Thread threadCons = new Thread(cons);

        threadProd.start();
        threadCons.start();
    }
}