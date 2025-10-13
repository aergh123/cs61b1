package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
        @Override
        public boolean equals(Object o){
            if (o instanceof MyHashMap<?,?>.Node other){
                return key==other.key;
            }
            return false;
        }
    }


    /* Instance Variables */
    ;
    // You should probably define some more!
    private int  initialCapacity=16;
    private Collection<Node>[] buckets=  new Collection[initialCapacity];
    private double  loadFactor=0.75;
    private int size=0;
    /** Constructors */
    public MyHashMap() {
        initializeBuckets(initialCapacity);
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity=initialCapacity;
        buckets = new Collection[initialCapacity];
        initializeBuckets(initialCapacity);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity=initialCapacity;
        this.loadFactor=loadFactor;
        buckets =  new Collection[initialCapacity];
        initializeBuckets(initialCapacity);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }
    private void initializeBuckets(int capacity) {
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }


    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    @Override
    public void put(K key, V value) {
        resize();
       int hashPosition=Math.floorMod(key.hashCode(),initialCapacity);
       Node item=new Node(key,value);
       for (Node node:buckets[hashPosition]){
           if(key.equals(node.key)){
               node.value=value;
              return;
           }
       }
       buckets[hashPosition].add(item);
       size++;
    }

    public void resize(){
        if((double) size/initialCapacity<loadFactor){
            return;
        }
        int newCapacity = initialCapacity * 2;
        Collection<Node>[] newBuckets = new Collection[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }
        for (int i=0;i<initialCapacity;i++){
            Collection<Node> bucket=buckets[i];
            if(bucket!=null){
                for (Node node:bucket){
                    int hashPosition=Math.floorMod(node.key.hashCode(),newCapacity);
                    newBuckets[hashPosition].add(node);
                }
            }
        }
        this.buckets = newBuckets;
        this.initialCapacity = newCapacity;
    }

    @Override
    public V get(K key) {
        if(!containsKey(key)){
            return null;
        }
        int hashPosition=Math.floorMod(key.hashCode(),initialCapacity);
        for (Node item:buckets[hashPosition]){
            if(key.equals(item.key)){
                return item.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int hashPosition=Math.floorMod(key.hashCode(),initialCapacity);
        if(buckets[hashPosition]==null){
            return false;
        }
        for (Node i:buckets[hashPosition]){
                if(key.equals(i.key)){
                    return true;
                }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets = new LinkedList[buckets.length];   // 使用当前数组的长度
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> item;
        item =new TreeSet<>();
       for (int i=0;i<initialCapacity;i++){
            Collection<Node> bucket=buckets[i];
            if(bucket!=null){
                for (Node node:bucket) {
                    item.add(node.key);
                }
            }
        }
        return item;
    }

    @Override
    public V remove(K key) {
        int hashPosition=Math.floorMod(key.hashCode(),initialCapacity);
        for (Node node:buckets[hashPosition]){
            if(key.equals(node.key)){
                V removeItem=node.value;
                buckets[hashPosition].remove(node);
                return removeItem;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }


}
