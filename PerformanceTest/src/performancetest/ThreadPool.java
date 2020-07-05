/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performancetest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author valtan
 */

public class ThreadPool {
    
    //numbers of simulated client
    private static final int NUMTHREAD = 20;
    
    //numbers of the all simulated operation
    private static final int NUMOPERATION = 100;
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUMTHREAD);
        for (int i = 0; i < NUMOPERATION; i++) {
            Runnable worker = new PerformanceTest("" + i);
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Tutti i thread sono terminati");
    }

}
