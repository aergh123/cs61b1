import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum=0;
        if (!L.isEmpty()){
         for (int i:L) {
             sum += i;
         }
         return sum;
        }
        else {
            return 0;
        }
        // TODO: Fill in this function.
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> Lis=new ArrayList<>();
        for (int i:L){
           if(i%2==0){
               Lis.add(i);
           }
        }

        // TODO: Fill in this function.
        return Lis;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> L3=new ArrayList<>();
        for (int i:L1){
            if (L2.contains(i)){
                L3.add(i);
            }
        }
        return L3;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count=0;
        for(String word:words){
            for(int i=0;i<word.length();i++){
                if(word.charAt(i)==c){
                    count++;
                }
            }
        }

        // TODO: Fill in this function.
        return count;
    }

    public static void main(String[] args) {
        List<Integer> L=List.of(1,2,3,4,5,6);

        System.out.println(evens(L));
    }
}
