package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque61B(Comparator<T> c){
        comparator=c;
    }
    public T max(){
        if(isEmpty()){
            return null;
        }

        int first= front();
        T maxItem=get(first);
        for (int i=0;i<size()-1;i++){
            T currentItem=get(first+1+i);
            if(comparator.compare(maxItem,currentItem)<0){
                maxItem=currentItem;
            }
        }
        return  maxItem;
    }
    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        int first= front();
        T maxItem=get(first);
        for (int i=0;i<size()-1;i++){
            T currentItem=get(first+1+i);
            if(c.compare(maxItem,currentItem)>0){
                maxItem=currentItem;
            }
        }
        return  maxItem;
    }
}
