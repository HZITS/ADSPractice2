package com.company;

public class MyStack<T extends Comparable<T>> {

    private MyLinkedList<T> stackNode;

    public MyStack() {
        stackNode = new MyLinkedList<T>();
    }

    boolean empty(){
        return stackNode.size() == 0;
    }

    int size(){
        return stackNode.size();
    }

    public T push(T newItem){
        stackNode.add(newItem, 0);
        return newItem;
    }

    public T peek(){
        return stackNode.get(0);
    }

    public T pop(){
        if (empty()) {
            throw new IndexOutOfBoundsException("stack is empty: index out of bounds");
        }
        return stackNode.remove(0);
    }

}
