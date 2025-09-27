import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character,Integer> c=new TreeMap<>();
        int code=97;
        for (int i=0;i<26;i++){
            c.put((char)(97+i),i+1);
        }
        return c;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer,Integer> squ=new TreeMap<>();
        for (int i:nums){
            squ.put(i,i*i);
        }
        return squ;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String,Integer> CountWords=new TreeMap<>();

        for (String word:words){
            int count=0;
            for (int i=0;i<words.size();i++){
                if (word.equals(words.get(i))){
                    count++;
                }
            }
            CountWords.put(word,count);
        }
        return CountWords;
    }

    public static void main(String[] args) {
      System.out.println(letterToNum());
    }
}
