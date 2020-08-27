package com.leetcode.problems.easy.print_in_order;

import java.util.concurrent.atomic.AtomicBoolean;

// https://leetcode.com/problems/print-in-order/
class Foo {

    private final AtomicBoolean firstExecuted, secondExecuted, thirdExecuted;
    // from problem description it sounds like there will ALWAYS be 3 calls, and to each of them, rather than a state-machine thing.
    public Foo() {
        firstExecuted = new AtomicBoolean(false);
        secondExecuted = new AtomicBoolean(false);
        thirdExecuted = new AtomicBoolean(false);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        firstExecuted.set(true);
        synchronized (secondExecuted) {
            secondExecuted.notify();
        }
        synchronized (thirdExecuted) {
            thirdExecuted.notify();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        if (!firstExecuted.get()) {
            synchronized(secondExecuted) {
                secondExecuted.wait();
            }
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        secondExecuted.set(true);
        synchronized (thirdExecuted) {
            thirdExecuted.notify();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!(firstExecuted.get() && secondExecuted.get())) {
            synchronized (thirdExecuted) {
                thirdExecuted.wait();
            }
        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
