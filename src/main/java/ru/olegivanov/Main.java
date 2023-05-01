package ru.olegivanov;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    final static String LETTERS ="RLRFR";
    final static int ROUTE_LENGTH=100;
    final static int THREADS = 1000;
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static void main(String[] args) {
        for (int i = 0; i < THREADS; i++) {
                new Thread (()->{
                    String route = generateRoute(LETTERS,ROUTE_LENGTH);
                    int frequency=(int) route.chars().filter(ch->ch == 'R').count();
                    synchronized(sizeToFreq) {
                        if(sizeToFreq.containsKey(frequency)) {
                            sizeToFreq.put(frequency,sizeToFreq.get(frequency)+1);
                        } else {
                            sizeToFreq.put(frequency,1);
                        }
                    }
                }).start();
        }

        System.out.println("Hello world!");
    }
    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}