package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double>  {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;
    private Exception IllegalArgumentException;


    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }
    public TimeSeries(TimeSeries ts){
        this.putAll(ts);
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        for (int i=startYear;i<=endYear;i++){
            if(ts.containsKey(i)){
                this.put(i,ts.get(i));
            }
        }

    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
//        if(this.isEmpty()){
//            return new ArrayList<>();
//        }
        return new ArrayList<>(keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        if(this.years()==null){
            return null;
        }
        List<Double> dataArray=new ArrayList<>();
        for (int year:this.years()){
            dataArray.add(this.get(year));
        }
        return dataArray;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries tempSeries=new TimeSeries();
        Set<Integer> keyset=new TreeSet<>(ts.keySet());
        keyset.addAll(this.keySet());
        for (int year:keyset){
            double value=0.00;
            if(ts.containsKey(year)){
                value+=ts.get(year);
            }
            if(this.containsKey(year)){
                value+=this.get(year);
            }
            tempSeries.put(year,value);
        }
        return tempSeries;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) throws Exception {
        // TODO: Fill in this method.
        if(ts==null){
            return null;
        }
        TimeSeries tempSeries=new TimeSeries();

        for (int year:this.years()){
            if(!ts.containsKey(year)){
                throw IllegalArgumentException;
            }
            double value1=ts.get(year);
            double value2=this.get(year);
            tempSeries.put(year,value2/value1);

        }
        return tempSeries;
    }

//    @Override
//    public Iterator<Integer> iterator() {
//        return new  TimeSeriesIterator();
//    }
//    public class TimeSeriesIterator implements Iterator<Integer>{
//        private int index;
//        public TimeSeriesIterator(){
//        }
//        @Override
//        public boolean hasNext() {
//            return index<years().size();
//        }
//        @Override
//        public Integer next() {
//            return years().get(index++);
//        }
//    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
