import java.util.ArrayList;
import java.util.List;

public class UnionFind {
    // TODO: Instance variables
    public List<Integer> disjoint;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        disjoint=new ArrayList<>(N);
        // TODO: YOUR CODE HERE
        for (int i = 0; i < N; i++) {
            disjoint.add(-1);
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int pos=disjoint.get(parent(v));
        return Math.abs(pos);

    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        int pos=disjoint.get(v);
        if(pos>-1){
            while (true){
                if(disjoint.get(pos)<-1){
                    return pos;
                }
                pos=disjoint.get(pos);
            }
        }else {
        return v;
        }
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if(parent(v1)==parent(v2)){
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v)throws IllegalArgumentException {
        // TODO: YOUR CODE HERE
        if(v<0||v>disjoint.size()){
            throw new  IllegalArgumentException();
        }
        int root=parent(v);
        int pos=v;
        while (pos!= root) {
            disjoint.set(pos, root);
            pos = disjoint.get(pos);
        }
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int root1=parent(v1);
        int root2=parent(v2);
        int size1=sizeOf(v1);
        int size2=sizeOf(v2);
        if(size1<size2){
            disjoint.set(root1,root2);
            disjoint.set(root2,-(size1+size2));
        }
        else if(size1>size2){
            disjoint.set(root2,root1);
            disjoint.set(root1,-(size1+size2));
        }
        else {
            if(root1>root2){
                disjoint.set(root2,root1);
                disjoint.set(root1,-(size1+size2));
            }
            else if(root2>root1){
                disjoint.set(root1,root2);
                disjoint.set(root2,-(size1+size2));
            }
        }
    }

}
