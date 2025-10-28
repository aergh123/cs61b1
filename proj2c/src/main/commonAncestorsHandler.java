package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import father.ansSeries;

public class commonAncestorsHandler extends NgordnetQueryHandler {

        private ansSeries ans;
        public commonAncestorsHandler(ansSeries ans){
            this.ans=ans;
        }
        @Override
        public String handle(NgordnetQuery q) {

//           return "hello";
            return ans.fatherKNumber(q.words(), q.k()).toString();
        }


}
