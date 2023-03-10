package com.leetcode.problems.medium.print_foobar_alternatively;

import java.util.concurrent.atomic.AtomicInteger;

class FooBar {
    private int n;
    private AtomicInteger lock;

    public FooBar(int n) {
        this.n = n;
        lock = new AtomicInteger();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(lock.get() % 2 != 0) {}
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            lock.getAndAdd(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(lock.get() % 2 != 1) {}
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            lock.getAndAdd(1);
        }
    }
}