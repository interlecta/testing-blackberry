package com.f1rst.blackberry.util;

import java.util.Stack;

/**
 *
 * @author ivaylo
 */
public class WorkQueue
{
//    private final int nThreads;
//    private final Vector queue;

    private final PoolWorker[] threads;
    private final Stack queue;

    public WorkQueue(int nThreads)
    {
//        this.nThreads = nThreads;
//        queue = new Vector();
        queue = new Stack();
        threads = new PoolWorker[nThreads];

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
//        Logger.log("execute");
        synchronized(queue) {
//            Logger.log("syncing queue");
//            queue.addElement(r);
            queue.push(r);
            try {
//                Logger.log("notifying queue");
                queue.notify();
//                Logger.log("notifying finished queue");
            } catch (IllegalMonitorStateException i){
//                Logger.log("IllegalMonitorStateException");
            }
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
//            Runnable r = null;
            Runnable r;

//            Logger.log("PoolWorker Started: " +this.getName());

            while (true) {
                synchronized(queue) {                    
//                    Logger.log("queue size: " + String.valueOf(queue.size()));
                    while (queue.isEmpty()) {
                        try
                        {
//                            Logger.log("queue wait");
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

//                    r = (Runnable) queue.removeFirst();
//                    r = (Runnable) queue.elementAt(0);
//                      Logger.log("EXE runnable.");
//                      try {
                          r = (Runnable) queue.pop();
//                          Logger.log("EXE runnable: " + r.toString());
//                      } catch (EmptyStackException e) {
//                          Logger.log("EmptyStackException");
//                      }
//                    queue.removeElementAt(0);

//                      Logger.log("queue size" + String.valueOf(queue.size()));
                }

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    if(r!=null)
                        r.run();
                    r=null;
                }
                catch (RuntimeException e) {
                    // You might want to log something here
//                    Logger.log("WorkQueue RTE" + e.getMessage());
                }
            }
        }
    }
}
