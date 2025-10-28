package father;

import ngrams.NGramMap;
import ngrams.TimeSeries;
import tu.wordSeries;

import java.time.Year;
import java.util.*;

public class ansSeries {
    private wordSeries words;
    private NGramMap map;

   public ansSeries(String  wordsFilename, String countsFilename,String synsetName,String hyponymsName){
       words=new wordSeries(synsetName,hyponymsName);
       map=new NGramMap(wordsFilename,countsFilename);
   }
//   public Map<String,Double> specificCase(String word,int startYear,int endYear,int k){
//       Set<String> wordAnswer=words.resultValue(word);
//       Map<String,Double> result=new TreeMap<>();
//       double resultNum=0;
//       for (String i:wordAnswer){
//           TimeSeries temp=map.countHistory(i,startYear,endYear);
//         for (int year=startYear;year<=endYear;year++){
//
//           if(temp.get(year)==null) {
//               continue;
//           }
//           resultNum+=temp.get(year);
//         }
//         if(result.size()<k|| haveMin(result,resultNum)){
//             result.put(i,resultNum);
//             resultNum=0;
//         }
//       }
//       return result;
//   }
    public Map<String,Double> specificCase(List<String> wordList, int startYear, int endYear, int k){
        Set<String> wordAnswer=words.getCommon(wordList);
        Map<String,Double> result=new TreeMap<>();
        double resultNum=0;
        for (String i:wordAnswer){
            TimeSeries temp=map.countHistory(i,startYear,endYear);
            for (int year=startYear;year<=endYear;year++){

                if(temp.get(year)==null) {
                    continue;
                }
                resultNum+=temp.get(year);
            }
            if(result.size()<k || haveMin(result,resultNum)){
                result.put(i,resultNum);
                resultNum=0;
            }
        }
        return result;
    }


   private boolean haveMin(Map<String,Double> result,double value){
       for (String i:result.keySet()){
           if(value>result.get(i)){
               result.remove(i);
               return true;
           }
       }
       return false;
   }
   public Set<String> fatherKNumber(List<String> wordList ,int k){
      return words.fatherKeepKNumber(wordList,k);
   }

//    public static void main(String[] args) {
//        String wordFile = "./data/ngrams/top_14377_words.csv";
//        String countFile = "./data/ngrams/total_counts.csv";
//        String synsetFile = "./data/wordnet/synsets.txt";
//        String hyponymFile = "./data/wordnet/hyponyms.txt";
//        ansSeries ans=new ansSeries(wordFile,countFile,synsetFile,hyponymFile);
//        List<String> example=new ArrayList<>();
//        example.add("coke");
//        example.add("change");
//        System.out.println(ans.specificCase(example,2000,2020,6).toString());
//        System.out.println(ans.fatherKeepKNumber(example,6).toString());
//
//    }
}
