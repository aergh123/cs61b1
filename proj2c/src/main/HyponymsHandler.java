package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import father.ansSeries;
import tu.wordSeries;

public class HyponymsHandler extends NgordnetQueryHandler {
    private ansSeries ans;
    public HyponymsHandler(ansSeries ans){
        this.ans=ans;
    }
    @Override
    public String handle(NgordnetQuery q) {
        return ans.specificCase(q.words(), q.startYear(), q.endYear(), q.k()).keySet().toString();

    }


}
