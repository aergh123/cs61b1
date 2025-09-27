import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61B<T> implements Deque61B <T> {
    private T[] array;
    private int  front;
    private int  rear;
    private int  size;
    //0 1 2 3 4 5 6 7
    public ArrayDeque61B(){
      array=(T[]) new Object[8];
      front=0;
      rear=1;
      size=0;
    }
    @Override
    public void addFirst(T x) {
        double Dsize=size;
        if(rear==front){
            array[front]=null;
            resize(array.length*2);
        } else if (Dsize/array.length<=0.25&&array.length>16){
            resize(4*size);
        }
        array[front]=x;
        front=Math.floorMod(front-1,array.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        double Dsize=size;
        if(rear==front){
            array[front]=null;
            resize(array.length*2);
        } else if (Dsize/array.length<=0.25&&array.length>16){
            resize(4*size);
        }
        array[rear]=x;
        rear=Math.floorMod(rear+1,array.length);
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
        double Dsize=size;
        if (Dsize/array.length<=0.25&&array.length>16){
            resize(4*size);
        }
        T removeItems=array[front];
        array[front]=null;
        front=Math.floorMod(front+1,array.length);
        size--;
        return removeItems;
    }

    @Override
    public T removeLast() {
        double Dsize=size;
        if (Dsize/array.length<=0.25&&array.length>15){
            resize(4*size);
        }
        T removeItems=array[rear];
        array[rear]=null;
        rear=Math.floorMod(rear-1,array.length);
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
            for (int i=0;i<size;i++){
                int oidIndex=(front+1+i)%array.length;
                newArray[i]=array[oidIndex];
            }
            array=newArray;
            front=newArray.length-1;
            rear=size;
    }

    @Override
    public int length() {
        return array.length;
    }
    public void insst(){
        System.out.println("hello");
    }
    
}
