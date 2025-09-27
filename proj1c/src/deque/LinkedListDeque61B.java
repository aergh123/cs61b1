package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>,Iterable<T>{
    public class Node{
        public Node prev;
        public T items;
        public Node next;

        public Node(Node p, T x, Node n) {
            this.prev=p;
            this.items=x;
            this.next=n;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque61B(){
        sentinel=new Node(null,null,null);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
    }

    @Override
    public void init(T x, int arraySize) {

    }

    @Override
    public void addFirst(T x) {
        Node newNode=new Node(sentinel,x,sentinel.next);
        sentinel.next.prev=newNode;
        sentinel.next=newNode;
        size+=1;
    }

    @Override
    public int front() {
        return 0;
    }

    @Override
    public void addLast(T x) {
        Node newNode=new Node(sentinel.prev,x,sentinel);
        sentinel.prev.next=newNode;
        sentinel.prev=newNode;
        size+=1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p=sentinel.next;
        while (p.items!=null){
            returnList.add(p.items);
            p=p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size==0){
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
        if(!isEmpty()){
            Node first=sentinel.next;
            T firstItems=first.items;
            if(size==1){
                sentinel.prev=sentinel;
                sentinel.next=sentinel;
            }
            else {
                sentinel.next=first.next;
                first.next.prev=sentinel;
            }
            first.next=null;
            first.prev=null;
            first.items=null;
            return firstItems;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if(!isEmpty()){
            Node last =sentinel.prev;
            T lastItems = last.items;
            if(size==1){
                sentinel.prev=sentinel;
                sentinel.next=sentinel;
            }
            else {
                sentinel.prev= last.prev;
                last.prev.next=sentinel;
            }
            last.next=null;
            last.prev=null;
            last.items=null;
            return lastItems;
        }
        return null;
    }

    @Override
    public T get(int index) {
        Node p=sentinel.next;
        int count=1;
        if(index<=0||index>size()||isEmpty()){
            return null;
        }
        while (p!=null){
            if(count==index){
                return p.items;
            }
            p=p.next;
            count++;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public void resize(int newSize) {
        return;
    }
    @Override
    public Iterator<T> iterator(){
        return new ListIterator();

    }

    @Override
    public boolean contains(T item) {
        Node p=sentinel;
        for (int i=0;i<size;i++){
            p=p.next;
            if(p.items==item){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque61B OtherObject){
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
    @Override
    public String toString(){
        StringBuilder x=new StringBuilder();
        x.append("[");
        Node p=sentinel;
        for (int i=0;i<size;i++){
            p=p.next;
            x.append(p.items);
            x.append(" ");
        }
        x.append("]");
        return x.toString();
    }

    private class ListIterator implements Iterator<T> {
        private  int wizPos;
        private  Node nextItem=sentinel;
        public ListIterator (){
            wizPos=0;
        }

        @Override
        public boolean hasNext() {
            return wizPos<size;
        }

        @Override
        public T next() {
            nextItem=nextItem.next;
            T returnItem=nextItem.items;
            wizPos+=1;
            return returnItem;
        }
    }
    public static void main(String[] args) {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addFirst("middle");
        lld1.addLast("back");
//        for (String s : lld1) {
//            System.out.println(s);
//        }
        Iterator<String> iterator = lld1.iterator(); // 需要 iterator() 方法
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld2.addLast("front");
        lld2.addFirst("middle");
        lld2.addLast("back");
        boolean x=lld1.equals(lld2);
        System.out.println(lld2);
    }
}
