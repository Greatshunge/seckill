package org.seckill.threadTest;

/**
 * Created by shunge on 2017/5/18.
 */
public class ThreadSynchronized {

    public static void main(String[] args) {
        new ThreadSynchronized().init();
    }

    private void init(){
        //使用内部类创建对象之前，应该先有外部类对象，所以在19行这么调用
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    outputer.output("nashinianshao");
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    outputer.output3("qingkuang");
                }
            }
        }).start();
    }

    static class Outputer{
        //String pubObject = "";
        //方法1 ： synchronize包在逻辑代码块外面，（）中的对象，可以用59行的pubObject作为两个线程的公共对象
        public void output(String name){
            int len = name.length();
            synchronized (Outputer.class){
                for (int i = 0;i<len;i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }
        //方法 2 ：synchronized直接写在方法名上即可（这样写的话，公共对象采用的就是23行new的对象）
        public synchronized void output2(String name){
            int len = name.length();
            for (int i=0;i<len;i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
        //方法 3 ：静态方法，采用类字节码作为公共对象
        public static synchronized void output3(String name){
            int len = name.length();
            for (int i=0;i<len;i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
