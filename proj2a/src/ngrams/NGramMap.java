package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.TreeMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private In wordsIn;
    private In countsIn;
    private TreeMap<String,TimeSeries> wordsData=new TreeMap<>();
    private TimeSeries countsData=new TimeSeries();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        wordsIn=new In(wordsFilename);
        countsIn=new In(countsFilename);
        if(wordsIn.isEmpty()){
            throw new IllegalArgumentException("Words file not found: " + wordsFilename);
        }
        if(countsIn.isEmpty()){
            throw new IllegalArgumentException("counts file not found: " + countsFilename);
        }
        processWords();
        processCounts();
        wordsIn.close();
        countsIn.close();
    }

    private void processWords() {
        while ((wordsIn.hasNextLine())){
            String line= wordsIn.readLine();
            String[] allParts=line.split("\t");

            String wordParts=allParts[0].trim();
            int yearParts=Integer.parseInt(allParts[1].trim());
            double numparts=Double.parseDouble(allParts[2].trim());

            TimeSeries otherParts=wordsData.get(wordParts);
            if(otherParts==null){
                otherParts=new TimeSeries();
                wordsData.put(wordParts,otherParts);
            }
            otherParts.put(yearParts,numparts);

        }

    }

    private void processCounts() {
        while ((countsIn.hasNextLine())){
            String line= countsIn.readLine();
            String[] allParts=line.split(",");

            int yearParts=Integer.parseInt(allParts[0].trim());
            double totalParts=Double.parseDouble(allParts[1].trim());
           countsData.put(yearParts,totalParts);
        }
    }


    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries tempSeries=new TimeSeries();
        TimeSeries resultSeries=new TimeSeries();

        if(wordsData.containsKey(word)){
            tempSeries=wordsData.get(word);

            for (int i=startYear;i<=endYear;i++) {
                if (tempSeries.containsKey(i)) {
                    resultSeries.put(i, tempSeries.get(i));
                }
            }
            return resultSeries;
        }
        return resultSeries;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries resultSeries=new TimeSeries();

        if(wordsData.containsKey(word)){
            resultSeries=wordsData.get(word);
            return resultSeries;
        }
        return resultSeries;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries result=new TimeSeries();
        result=countsData;
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries result=new TimeSeries();
        TimeSeries tempCountsSeries =totalCountHistory();

        if(wordsData.containsKey(word)){
            TimeSeries tempWordsSeries=wordsData.get(word);
            for (int i=startYear;i<=endYear;i++){
                if(tempCountsSeries.containsKey(i)&&tempWordsSeries.containsKey(i)){
                     double relative=(double) tempWordsSeries.get(i)/tempCountsSeries.get(i);
                     result.put(i,relative);
                }else {
                    continue;
                }
            }
        }

        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries result=new TimeSeries();
        TimeSeries tempCountsSeries =totalCountHistory();
        if(wordsData.containsKey(word)){
            TimeSeries tempWordsSeries=wordsData.get(word);

            for (int year:tempWordsSeries.years()){
                if(tempCountsSeries.containsKey(year)&&tempWordsSeries.containsKey(year)){
                    double relative=(double) tempWordsSeries.get(year)/tempCountsSeries.get(year);
                   result.put(year,relative);
                }else {
                    continue;
                }
            }
        }
        return result;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries result=new TimeSeries();
        TimeSeries tempCountsSeries =totalCountHistory();


        for (int year=startYear;year<=endYear;year++){
            double sum=0.0;
            if(!tempCountsSeries.containsKey(year)){
                continue;
            }
            for (String word:words){
                if(wordsData.containsKey(word)&&wordsData.get(word).containsKey(year)){
                    double wordCount=wordsData.get(word).get(year);
                    double totalCount=tempCountsSeries.get(year);
                    sum+= wordCount/totalCount;
                }
            }
            result.put(year,sum);

        }

        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return summedWeightHistory(words,TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
