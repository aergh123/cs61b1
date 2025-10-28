package main;

import browser.NgordnetQueryHandler;
import father.ansSeries;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        ansSeries ans =new ansSeries(wordFile,countFile,synsetFile,hyponymFile);
        return new  HyponymsHandler(ans);
//        throw new RuntimeException("Please fill out AutograderBuddy.java!");
    }
}
