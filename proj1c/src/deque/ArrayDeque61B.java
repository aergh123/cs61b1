package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] array;
    private   int  front=0;
    private int  rear=0;
    private int  size=0;
    //0 1 2 3 4 5 6 7
    public ArrayDeque61B(){
        array=(T[]) new Object[8];
        front=0;
        rear=1;
        size=0;
    }
    public ArrayDeque61B(int arraySize){
        array=(T[]) new Object[arraySize];
        front=0;
        rear=1;
        size=0;
    }
    @Override
    public void init(T x, int arraySize){
        for (int i=0;i<arraySize;i++){
            array[i]=x;
        }
    }
    @Override
    public void addFirst(T x) {
        if(rear==front){
            return;
        }
        array[front]=x;
        front=Math.floorMod(front-1,array.length);
        size++;
    }
    @Override
    public int  front(){
        return front;
    }


    @Override
    public void addLast(T x) {
//        double Dsize=size;
            if(rear==front){
                return;
            }
//            resize(array.length*2);
//        } else if (Dsize/array.length<=0.25&&array.length>16){
//            resize(4*size);
//        }
        int latIndex=Math.floorMod(rear+1,array.length);
        array[rear]=x;
        rear=latIndex;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = (front + 1 + i) % array.length; // 从 front+1 开始循环访问
            list.add(array[index]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        if(size==0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
//        double Dsize=size;
//        if (Dsize/array.length<=0.25&&array.length>16){
//            resize(4*size);
//        }
        int firstIndex=Math.floorMod(front+1,array.length);
        T removeItems=array[firstIndex];
        array[firstIndex]=null;
        front=firstIndex;
        size--;
        return removeItems;
    }

    @Override
    public T removeLast() {
//        double Dsize=size;
//        if (Dsize/array.length<=0.25&&array.length>15){
//            resize(4*size);
//        }
        int lastIndex=Math.floorMod(rear-1,array.length);
        T removeItems=array[lastIndex];
        array[lastIndex]=null;
        rear=lastIndex;
        size--;
        return removeItems;
    }

    @Override
    public T get(int index) {

        return array[index] ;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    @Override
    public void resize(int newSize) {
        T[] newArray=(T[]) new Object[newSize];
        for (int i=0;i<array.length;i++){
            int oidIndex=(front+1+i)%array.length;
            newArray[i]=array[oidIndex];
        }
        array=newArray;
        front=newArray.length-1;
        rear=array.length;
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private  int wizPos;
        private int i;
        public ArrayIterator(){
            wizPos=(front+1+i)%array.length;
        }

        @Override
        public boolean hasNext() {
            return i<size;
        }

        @Override
        public T next() {
            wizPos=(front+1+i)%array.length;
            T returnItem= array[wizPos];
            i++;
            return returnItem;
        }
    }
    @Override
    public boolean contains(T item){
        for (int i = 0; i < size; i++) {
            int index = (front + 1 + i) % array.length;
            if(array[index]==item){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof ArrayDeque61B OtherObject){
            if(this.size!=OtherObject.size){
                return false;
            }else {
               for (T i:this){
                   if(!OtherObject.contains(i)){
                       return false;
                   }
               }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addLast("front");
        lld1.addFirst("middle");
        lld1.addLast("back");

        Deque61B<String> lld2 = new ArrayDeque61B<>();
        lld2.addLast("front");
        lld2.addFirst("middle");
        lld2.addLast("back");
        boolean x=lld1.equals(lld2);
        System.out.println(x);

    }

}
