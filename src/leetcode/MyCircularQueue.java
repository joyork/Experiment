package leetcode;

import java.util.ArrayList;

public class MyCircularQueue {

    private int[] queue;
    private int head;
    private int tail;
    private int size;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        queue = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) return false;
        if(isEmpty()) {
            head = 0;
        }
        tail = (tail+1) % size;
        queue[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) return false;
        if(head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head+1) % size;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        return queue[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        return queue[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }

    public static void main(String[] args) {
        int k = 5;
        int value = 9;
        MyCircularQueue obj = new MyCircularQueue(k);
        boolean param_1 = obj.enQueue(value);
        boolean param_2 = obj.deQueue();
        int param_3 = obj.Front();
        int param_4 = obj.Rear();
        boolean param_5 = obj.isEmpty();
        boolean param_6 = obj.isFull();
        System.out.println(param_1 + " " + param_2 + " " + param_3 + " "+ param_4 + " " + param_5 + " "+ param_6);

        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(1));  // 返回 true
        System.out.println(circularQueue.enQueue(2));  // 返回 true
        System.out.println(circularQueue.enQueue(3));  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 false，队列已满
        System.out.println(circularQueue.Rear());  // 返回 3
        System.out.println(circularQueue.isFull());  // 返回 true
        System.out.println(circularQueue.deQueue());  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 true
        System.out.println(circularQueue.Rear());  // 返回 4

    }
}
