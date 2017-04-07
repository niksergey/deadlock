package stc5.lesson5;

/**
 * Created by sergey on 07.04.17.
 */

class SomeClass {
    synchronized void firstMethod(SomeClass ob) {
        String name = Thread.currentThread().getName();

        System.out.println(name + " in method SomeClass.firstMethod()");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " try to call AnotherClass.last()");
        ob.last();
    }

    synchronized void secondMethod(SomeClass ob) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " in AnotherClass.secondMethod()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " try to call AnotherClass.last()");
        ob.last();
    }

    synchronized private void last() {
        System.out.println("In method SomeClass.last()");
    }
}

public class MyThread implements Runnable {
    Thread t;

    private SomeClass firstObj = new SomeClass();
    private SomeClass secondObj = new SomeClass();

    MyThread(String name) {
        Thread.currentThread().setName("First Thread");
        t = new Thread(this, name);

        t.start();

        firstObj.firstMethod(secondObj);

        System.out.println("Back to first thread");
    }

    public void run() {
        secondObj.secondMethod(firstObj);
        System.out.println("Back to another thread");
    }
}
