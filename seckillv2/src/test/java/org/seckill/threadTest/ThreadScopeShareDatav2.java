package org.seckill.threadTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shunge on 2017/5/18.
 */
public class ThreadScopeShareDatav2 {

    private static int data = 0;
    private static Map<Thread,Integer> threadData = new HashMap<Thread, Integer>();
    public static void main(String[] args){

        new Thread(new Runnable() {
            public void run() {
                data = 111;
                System.out.println(Thread.currentThread().getName()+"has put data:"+data);
                threadData.put(Thread.currentThread(),data);
                new A().get();
                new B().get();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                data = 222;
                System.out.println(Thread.currentThread().getName()+"has put data:"+data);
                threadData.put(Thread.currentThread(),data);
                new A().get();
                new B().get();
            }
        }).start();
    }

    static class A{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from"+Thread.currentThread().getName()+"get data :"+data);
        }
    }

    static class B{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from"+ Thread.currentThread().getName()+"get data:"+data);
        }
    }
}
