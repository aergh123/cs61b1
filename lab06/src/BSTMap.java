import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<k extends Comparable<k> ,v > implements Map61B<k,v>{

    private class BSTNode{
        private k key;
        private BSTNode left;
        private BSTNode right;
        private v items;

        public BSTNode(k key,v i){
            this.key=key;
            this.items=i;
        }
    }
    private BSTNode root;
    private int size;
    public  BSTMap(){
      this.root=null;
      this.size=0;
    }

    @Override
    public void put(k key, v value) {
        if(key==null){
            return;
        }
        root=compareToRoot(root,key,value);
    }
    private BSTNode compareToRoot(BSTNode r, k key,v value){
        if(r==null){
            size++;
            return new BSTNode(key,value);
        }
        int  ctr=key.compareTo(r.key);
        if(ctr<0){
           r.left=compareToRoot(r.left,key,value);
        } else if (ctr>0) {
            r.right=compareToRoot(r.right,key,value);
        }
        else {
            r.items=value;
        }
        return r;
    }

    @Override
    public v get(k key) {
        BSTNode temporal=returnKeyNode(root,key);
        if(temporal==null){
            return null;
        }
        return returnKeyNode(root,key).items;
    }
    private BSTNode returnKeyNode(BSTNode r, k key){
        if(r==null){
            return null;
        }
        int  ctr=key.compareTo(r.key);
        if(ctr<0){
            return returnKeyNode(r.left,key);
        } else if (ctr>0) {
            return returnKeyNode(r.right,key);
        }
        else {
            return r;
        }
    }

    @Override
    public boolean containsKey(k key) {

        return returnKeyNode(root, key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root=null;
        size=0;
    }

    @Override
    public Set<k> keySet() {
        return null;
    }

    @Override
    public v remove(k key) {
        return null;
    }

    @Override
    public Iterator<k> iterator() {
        return new BSTMapIterator();
    }
    public class BSTMapIterator implements Iterator<k>{
       private int index=0;
       private ArrayList<k> MapArray =new ArrayList<>(size);
        public BSTMapIterator(){
             BSTOrder(root);
        }
        @Override
        public boolean hasNext() {
            return index<MapArray.size();
        }

        @Override
        public k next() {

           return MapArray.get(index++);
        }
        private void BSTOrder(BSTNode r){
            if(r==null){
                return;
            }
            BSTOrder(r.left);
            MapArray.add(r.key);
            BSTOrder(r.right);
        }
    };





}
