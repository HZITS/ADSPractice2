package com.company;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {

    private static class MyNode<T>{
        T data;
        MyNode<T> next, prev;

        MyNode(T data){
            this.data = data;
        }
    }

    private int length = 0;
    private MyNode<T> head, tail;

    public MyLinkedList() {};

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object o) {
        MyNode<T> temp = head;
        for (int i = 0; i < length; i++) {
            if (temp.data == o) return true;
            temp = temp.next;
        }
        return false;
    }

    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    @Override
    public void add(T item, int index) {
        MyNode<T> newNode = new MyNode<>(item);
        if (index > length || index < 0)
            throw new IndexOutOfBoundsException("index should either be positive or >= size");
        if (index == 0) addAtTheHead(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            int counter = 0;
            for (MyNode<T> i = head; i != null; i = i.next) {
                counter++;
                if (counter == index) {
                    newNode.prev = i;
                    if (i.next != null) {
                        i.next.prev = newNode;
                        newNode.next = i.next;
                        i.next = newNode;
                    }
                    else{
                        newNode.prev = tail;
                        tail.next = newNode;
                        tail = newNode;
                    }
//                    i.next = newNode;
                    break;
                }
            }
        }
        length++;
    }

    private void addAtTheHead(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

    @Override
    public boolean remove(T item) {
        MyNode<T> temp = head;
        if (contains(item)) {
            while (temp.data != item) {
                temp = temp.next;
            }
            if (temp.next != null) temp.next.prev = temp.prev;
            else tail = temp.prev;
            if (temp.prev != null) temp.prev.next = temp.next;
            else head = temp.next;

            length--;
            return true;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and less than size!");
        MyNode<T> temp = head;
        while (index != 0) {
            temp = temp.next;
            index--;
        }
        if (temp.next != null) temp.next.prev = temp.prev;
        else tail = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        else head = temp.next;

        length--;
        return temp.data;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            remove(i);
        }
        length = 0;
    }

    @Override
    public T get(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and < size");

        MyNode<T> temp = head;

        while (index != 0) {
            temp = temp.next;
            index--;
        }

        return temp.data;
    }

    @Override
    public int indexOf(Object o) {
        MyNode<T> temp = head;
        for (int i = 0; i < length; i++) {
            if (temp.data==o) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        MyNode<T> temp = tail;
        for (int i = length - 1; i > 0; i--) {
            if (temp.data==o) {
                return i;
            }
            temp = temp.prev;
        }
        return -1;
    }

    @Override
    public void sort() {
        for (int i = 0; i < length; i++) {
            MyNode<T> temp_j = head;
            int counter = 0;
            for (int j = 0; j < length - i - 1; j++) {
                if (temp_j.data.compareTo(temp_j.next.data) > 0) {
                    counter++;
                    T temp = temp_j.data;
                    temp_j.data = temp_j.next.data;
                    temp_j.next.data = temp;
                }
                temp_j = temp_j.next;
            }
            if (counter == 0) break;
        }
    }

}
