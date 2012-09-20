package com.f1rst.blackberry.util;


/**
 *
 * @author ivaylo
 */
public class ThreadPoolFactory {

    private final static int nThreads = 4;//15;//12;//7;//2 4

    static WorkQueue w;// = new WorkQueue(nThreads);

    public static /*synchronized*/ void execute(ExtendedRunnable runnable){
        getWorker().execute(runnable);
    }

    public static /*synchronized*/ void execute(Runnable runnable){
        getWorker().execute(runnable);
    }

    private static synchronized WorkQueue getWorker(){
        if(w == null) {
            w = new WorkQueue(nThreads);
        }
        return w;
    }

}
