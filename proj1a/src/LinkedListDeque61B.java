import net.sf.saxon.expr.Component;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class LinkedListDeque61B<T> implements Deque61B<T> {

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
    public void addFirst(T x) {
        Node newNode=new Node(sentinel,x,sentinel.next);
        sentinel.next.prev=newNode;
        sentinel.next=newNode;
        size+=1;
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

    public static void main(String[] args) {
        System.out.println(4/8);
    }
}
