package tu;

import java.util.*;

public class directedGraph<K,V>{

    public class Node<K,V>{
        public List<V> value=new ArrayList<>();
        public Set<K> neighbour=new HashSet<>();
        Node(V v){
            this.value.add(v);
        }
        Node(V v,K nei){

            this.value.add(v);
            this.neighbour.add(nei);
        }

        public void addValue(V v){
            if(!value.contains(v)){
              value.add(v);
            }
        }
        public List<V> getValue(){
            return value;
        }
        public Set<K> getNeighbour(){
            return neighbour;
        }
        public void addNeighbour(K key){
            neighbour.add(key);
        }
        public String printValue(){
            String result="";
            for (V i:value){
                result+=i+" ";
            }
            return result;
        }
        public String printNeighbour(){
            String result="";
            for (K i:neighbour){
                result+=i+" ";
            }
            return result;
        }
    }
    private Map<K,Node<K,V>>Vertice;
    private int numVertice;
    private int numEdge;

    public directedGraph(){
        Vertice=new HashMap<>();
        numEdge=0;
        numVertice=0;
    }

    public void addVertex(K key,V newvalue){
        if(!Vertice.containsKey(key)){
            Node<K,V> node=new Node<>(newvalue);
            Vertice.put(key,node);
            numVertice++;
        }
        else {
                Vertice.get(key).addValue(newvalue);
        }
    }
    public void addEdges(K from ,K to){
        if (containsNeighbour(from,to)){
            return;
        }
        if(Vertice.containsKey(from)&& Vertice.containsKey(to)){
            Vertice.get(from).addNeighbour(to);
        }
    }
    public boolean containsNeighbour(K from,K to){
        return Vertice.get(from).getNeighbour().contains(to);
    }
    public void toSting(){
        System.out.println("Key:"+Vertice.keySet()+"\nvalue "+Vertice.get(1).printNeighbour());
    }

    public static void main(String[] args) {
        directedGraph<Integer,String> tu=new directedGraph<>();
        tu.addVertex(1,"a");
        tu.addVertex(2,"b");
        tu.addVertex(1,"c");
        tu.addEdges(1,2);
        tu.toSting();
    }

}
