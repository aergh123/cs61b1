import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean [][] site;
    WeightedQuickUnionUF disjoint;
    private int count;
    private int num;
    private int topNode;       // 虚拟顶部节点
    private int bottomNode;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        this.num=N;
        int size=num*num+2;
        site=new boolean[num][num];
        disjoint=new WeightedQuickUnionUF(size);
        for (int i=0;i<num;i++){
            for (int j=0;j<num;j++){
                site[i][j]=false;
            }
        }
        topNode=num*num;
        bottomNode=num*num+1;

    }

//              0 1 2 3 4
//            0 0 0 0 0 0
//            1 0 0 0 0 0
//            2 0 0 0 0 0
//            3 0 0 0 0 0
//            4 0 0 0 0 0



    public void open(int row, int col) {
        // TODO: Fill in this method.
        if(site[row][col]){
           return;
        }
        site[row][col]=true;
        count++;
        int index=row* num +col;
        int[] row1 ={1,0,-1,0};
        int[] col1 ={0,1,0,-1};

        if (row == 0) {
            disjoint.union(index, topNode);
        }
        for (int i=0;i<4;i++){
            int r=row+row1[i];
            int c=col+col1[i];
            if(r>=0&&r< num &&c>=0&&c< num){
                if(site[r][c]){
                    int neighborIndex=r* num +c;
                    disjoint.union(index,neighborIndex);
                }
            }
        }
        if (row == num - 1 &&connectToTop(row, col)) {
            disjoint.union(index, bottomNode);
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return site[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return connectToTop(row, col);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return count;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return disjoint.connected(topNode,bottomNode);
    }

    public boolean connectToTop(int row,int col){
        if(!site[row][col]){
            return false;
        }
        int index=row*num+col;
        return disjoint.connected(topNode,index);
    }


    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
