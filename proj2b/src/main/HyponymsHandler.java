package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import tu.wordSeries;

public class HyponymsHandler extends NgordnetQueryHandler {
    private wordSeries word;
    public HyponymsHandler(wordSeries word){
        this.word=word;
    }
    @Override
    public String handle(NgordnetQuery q) {
        if(q.words().size()==1){
        return word.singleToString(q.words().getFirst());
        }
        else {
            return word.muitiToString(word.getCommon(q.words()));
        }
    }


}
