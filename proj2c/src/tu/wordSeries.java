package tu;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class wordSeries {
    private In synsetIn;
    private In hyponymIn;
    Set<String> result=new TreeSet<>();
    private directedGraph<Integer,String> wordGraph =new directedGraph<>();


    public wordSeries(String synsetName,String hyponymsName){
        synsetIn=new In(synsetName);
        hyponymIn=new In(hyponymsName);
        processSynsets();
        processHyponyms();
        synsetIn.close();
        hyponymIn.close();
    }
    public void processSynsets(){
        while (synsetIn.hasNextLine()){
            String line= synsetIn.readLine();
            String[] allParts=line.split(",");
            String[] wordsValuePart=allParts[1].split(" ");

            int wordKey=Integer.parseInt( allParts[0].trim());
            for (String value:wordsValuePart){
              wordGraph.addVertex(wordKey,value);
            }

        }
    };
    public void   processHyponyms(){
        while (hyponymIn.hasNextLine()){
            String line= hyponymIn.readLine();
            String[] wordsNeighbourPart=line.split(",");
            int wordKey=Integer.parseInt(wordsNeighbourPart[0].trim());
            for (int i=1;i<wordsNeighbourPart.length;i++){
                int neighbourKey=Integer.parseInt(wordsNeighbourPart[i]);
                wordGraph.addEdges(wordKey,neighbourKey);
               wordGraph.setFather(neighbourKey,wordKey);
            }
        }
    };

    public Set<String> resultValue(String word){
        result.clear();
        for (int wordKey: wordGraph.getKey(word)){
         if(!wordGraph.containsKey(wordKey)){
            result.add("not found");
            return result;
         }
         List<String> synsetValue = wordGraph.getValue(wordKey);
         result.addAll(synsetValue);

        helper(wordKey);
       }

       return new TreeSet<>( result);
    }

    private void helper(int key){
        if(wordGraph.isEmptyNeighbour(key)){
            return ;
        }
        for (int i: wordGraph.getNeighbor(key)){
            List<String>  hyponymValue= wordGraph.getValue(i);
            result.addAll(hyponymValue);
            helper(i);
        }
    }

    public Set<String> getCommon(List<String> wordList){
        if (wordList == null || wordList.isEmpty()) {
            return null;
        }
        // 验证所有节点都存在
      for (String word:wordList){
        for (int wordKey: wordGraph.getKey(word)) {
            if (!wordGraph.containsKey(wordKey)) {
                return null;
            }
        }
      }
        Set<String> commonValues = resultValue(wordList.getFirst());
      for (int i=1;i<wordList.size();i++){

          Set<String> descendants = resultValue(wordList.get(i));
          commonValues.retainAll(descendants);
          // 如果交集已经为空，提前结束
          if (commonValues.isEmpty()) {
              break;
          }
      }
      return commonValues;
    }

    public Set<Integer> getAllFather(String word){

        Set<Integer>  father=new TreeSet<>();
        for (int wordKey:wordGraph.getKey(word)){
            father=fatherHelper(father,wordKey);
            father.add(wordKey);
        }
        return father;
    }

    private Set<Integer> fatherHelper(Set<Integer> father,int key){
        if( wordGraph.getFather(key)!=null){
           int fatherKey= wordGraph.getFather(key);
           father.add(fatherKey);
           father=fatherHelper(father,fatherKey);
        }
        return father;
    }
    public Set<Integer> getFatherCommon(List<String> wordList){
        if (wordList == null || wordList.isEmpty()) {
            return null;
        }
        // 验证所有节点都存在
        for (String word:wordList){
            for (int wordKey: wordGraph.getKey(word)) {
                if (!wordGraph.containsKey(wordKey)) {
                    return null;
                }
            }
        }
        Set<Integer> commonFather = getAllFather(wordList.getFirst());
        for (int i=1;i<wordList.size();i++){
            Set<Integer> descendants = getAllFather(wordList.get(i));
            commonFather.retainAll(descendants);
            // 如果交集已经为空，提前结束
            if (commonFather.isEmpty()) {
                break;
            }
        }
        return commonFather;
    }

    public Set<String> fatherToString(List<String> wordList){
        Set<String> father=new TreeSet<>();
      if(wordList.size()==1){
        for (int i:getAllFather(wordList.getFirst())){
            father.addAll(wordGraph.getValue(i));
        }
          return father;
      }else {
       for (int i:getFatherCommon(wordList)){
            father.addAll(wordGraph.getValue(i));
        }
        return father;
      }
    }
    public Set<String> fatherKeepKNumber(List<String> wordList ,int k){
        Set<String>  result=new TreeSet<>();
            for (String value:fatherToString(wordList)){
                result.add(value);
                if(result.size()>=k){
                    return result;
                }
            }
        return result;
    }


    public String singleToString(String word){
        String finl="";
        finl+=resultValue(word).toString();
        return finl;
    }
    public String muitiToString(Set<String> consquence){
        String finl="";
        finl+=consquence.toString();
        return finl;
    }

    public static void main(String[] args) {
        String synsetFile = "./data/wordnet/synsets16.txt";
        String hyponymFile = "./data/wordnet/hyponyms16.txt";
        wordSeries word=new wordSeries(synsetFile,hyponymFile);
        List<String> value=new ArrayList<>();
        value.add("change");
        value.add("coke");
        System.out.println(word.fatherKeepKNumber(value,6).toString());
//     System.out.println(word.singleToString("occurrence"));
//
//      System.out.println(  word.muitiToString(word.getCommon(value)));
   }
}
