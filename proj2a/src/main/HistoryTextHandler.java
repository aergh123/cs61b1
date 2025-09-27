package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap map;
    private TimeSeries result;
    public HistoryTextHandler(NGramMap map){
        this.map=map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        String response="";
        for (String x:q.words()){
            response+=x+": ";
            result=map.weightHistory(x, q.startYear(), q.endYear());
            response+=result.toString()+"\n";
        }
        return response;
    }
}
