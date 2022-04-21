package com.company;

public class MyQueue<T extends Comparable<T>> {

    private MyLinkedList<T> queueNode;

    public MyQueue() {
        queueNode = new MyLinkedList<T>();
    }

    public boolean empty(){
        return queueNode.size() == 0;
    }

    public int size(){
        return queueNode.size();
    }

    public T peek(){
        return queueNode.get(0);
    }

    public T enqueue(T newItem){
        queueNode.add(newItem);
        return newItem;
    }

    public T dequeue(){
        if (empty()) {
            throw new IndexOutOfBoundsException("queue is empty: index out of bounds");
        }
        return queueNode.remove(0);
    }
}
