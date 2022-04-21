package com.company;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {

    private Object[] arr;
    private int length = 0;
    private int capacity = 3;

    public MyArrayList(){
        arr = new Object[capacity];
    };

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object o) {
        for (int i=0; i < arr.length; i++){
            if (arr[i] == o){
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T item) {
        if (length == capacity){
            increaseCapacity();
        }
        arr[length++] = item;
    }

    private void increaseCapacity() {
        capacity = 2*capacity;
        Object[] oldArr = arr;
        arr = new Object[capacity];

        for (int i=0; i < oldArr.length; i++){
            arr[i] = oldArr[i];
        }
    }

    @Override
    public void add(T item, int index) {
        for (int i=index; i <= arr.length; i++){
            arr[i+1]=arr[i];
        }
        arr[index] = item;
    }

    @Override
    public boolean remove(T item) {
        for (int i=0; i < arr.length; i++){
            if (arr[i] == item){
                afterItemDeletionRelocation(i);
                return true;
            }
        }
        return false;
    }

    private void afterItemDeletionRelocation(int i) {
        for (int j=i; j<arr.length; j++){
            arr[j] = arr[j+1];
        }
    }

    @Override
    public T remove(int index) {
        T removalItem = (T)arr[index];
        afterIndexDeletionRelocation(index);
        return removalItem;
    }

    private void afterIndexDeletionRelocation(int index) {
        for (int j=index; j<arr.length; j++){
            arr[j] = arr[j+1];
        }
    }

    @Override
    public void clear() {
        for (int j=0; j<arr.length; j++){
            remove(j);
        }
        length = 0;
    }

    @Override
    public T get(int index) {
        return (T)arr[index];
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (int i=0; i<arr.length; i++){
            if (arr[i]==o){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        for (int i=0; i<arr.length; i++){
            if (arr[i]==o){
                index = i;
            }
        }
        return index;
    }

    @Override
    public void sort() {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                Comparable<T> a = (T) arr[j];
                if (a.compareTo((T) arr[j+1]) > 0) {
                    Object temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public void swap(int i, int next) {
        Object temp = arr[i];
        arr[i] = arr[next];
        arr[next] = temp;
    }
}
