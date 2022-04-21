package com.company;

public class MyHeap<T extends Comparable<T>> {
    private MyArrayList<T> heapList;

    public MyHeap(){
        heapList = new MyArrayList<T>();
    }

    public void add(T item) {
        heapList.add(item);
        heapify();
    }

    private void heapify() {
        for (int i = heapList.size() / 2; i >= 0; --i){
            maxHeapify(i);
        }
    }

    private void maxHeapify(int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int max;
        if (left < heapList.size() && heapList.get(left).compareTo(heapList.get(i)) > 0) {
            max = left;
        } else max = i;
        if (right < heapList.size() && heapList.get(right).compareTo(heapList.get(i)) > 0) {
            if (heapList.get(right).compareTo(heapList.get(left)) > 0) max = right;
        }
        if (max != i) {
            heapList.swap(i, max);
            maxHeapify(max);
        }
    }

    public T removeRoot() {
        if (heapList.size() == 0) {
            throw new IndexOutOfBoundsException("heap is empty: index out of bounds");
        }
        T temp = heapList.get(0);
        heapList.swap(0, heapList.size() - 1);
        heapList.remove(heapList.size() - 1);
        heapify();
        return temp;
    }

    public boolean remove(T item) {
        for (int i = 0; i < heapList.size(); i++) {
            if (heapList.get(i) == item) {
                heapList.swap(i, heapList.size() - 1);
                heapList.remove(heapList.size() - 1);
                heapify();
                return true;
            }
        }
        return false;
    }

}
