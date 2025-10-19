package tu;

import java.util.*;

public class directedGraph<K,V>{

    public class Node<k, v>{
        public List<v> value=new ArrayList<>();
        public Set<k> neighbour=new HashSet<>();
        Node(v v){
            this.value.add(v);
        }
        Node(v v, k nei){
            this.value.add(v);
            this.neighbour.add(nei);
        }

        public void addValue(v v){
            if(!value.contains(v)){
              value.add(v);
            }
        }
        public List<v> getListValue(){
            return value;
        }
        public boolean isEmpty(){
           return neighbour.isEmpty();
        }
        public Set<k> getListNeighbour(){
            return neighbour;
        }

        public void addNeighbour(k key){
            neighbour.add(key);
        }

        public String printValue(){
            String result="";
            for (v i:value){
                result+=i+" ";
            }
            return result;
        }
        public String printNeighbour(){
            String result="";
            for (k i:neighbour){
                result+=i+" ";
            }
            return result;
        }
    }
    private Map<K,Node<K,V>>Vertice;
    private Map<V,List<K>> valueToKey=new HashMap<>();
    private int numVertice;
    private int numEdge;

    public directedGraph(){
        Vertice=new HashMap<>();
        numEdge=0;
        numVertice=0;
    }

    public void addVertex(K key,V newValue){
        if(!Vertice.containsKey(key)){
            Node<K,V> node=new Node<>(newValue);
            Vertice.put(key,node);
            numVertice++;
        }
        else {
                Vertice.get(key).addValue(newValue);
        }
        if(valueToKey.get(newValue)==null){
            List<K> keyList=new ArrayList<>();
            keyList.add(key);
            valueToKey.put(newValue,keyList);
        }
        else {
            valueToKey.get(newValue).add(key);
        }
    }
    public void addEdges(K from ,K to){

        if(Vertice.containsKey(from)&& Vertice.containsKey(to)){
            Vertice.get(from).addNeighbour(to);
        }
    }

    public List<K> getKey(V value){
        if(!valueToKey.containsKey(value)){
            throw  new IllegalArgumentException("Words  not found: " );
        }
        return valueToKey.get(value);
    }
    public List<V> getValue(K key){
       return Vertice.get(key).getListValue();
    }
    public Set<K> getNeighbor(K key){
        Vertice.get(key);
        return Vertice.get(key).getListNeighbour();
    }
    public boolean isEmptyNeighbour(K key){
        return Vertice.get(key).isEmpty();
    }
    public boolean containsKey(K key){return Vertice.containsKey(key);}
    public boolean containsNeighbour(K from,K to){
        return Vertice.get(from).getListNeighbour().contains(to);
    }
    public void toSting(){
        System.out.println("Key:"+Vertice.keySet()+"\nvalue "+Vertice.get(1).printNeighbour());
    }



}
