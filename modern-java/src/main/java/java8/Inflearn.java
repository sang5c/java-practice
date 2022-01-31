package java8;

public class Inflearn {

    public static void main(String[] args) throws InterruptedException {
        // 1
        MyThread m = new MyThread();
        m.start();
        System.out.println("hello ");

        // 2
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread().getName());
            }
        });
        thread.start();
        System.out.println("hello " + Thread.currentThread().getName());

        // 3
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        thread2.start();
        System.out.println("hello: " + Thread.currentThread().getName());

        // 4
        Thread t = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        t.start();

        System.out.println("hello: " + Thread.currentThread().getName());
        t.join();
        System.out.println(t + "is finished");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("hello: " + Thread.currentThread().getName());
        }
    }

}
