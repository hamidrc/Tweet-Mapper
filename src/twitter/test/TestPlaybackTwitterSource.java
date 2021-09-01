package twitter.test;

import filters.Filter;
import org.junit.jupiter.api.Test;
import twitter.PlaybackTwitterSource;
import twitter4j.Status;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlaybackTwitterSource {

    @Test
    public void testSetup() {
        PlaybackTwitterSource source = new PlaybackTwitterSource(1.0);
        TestObserver to = new TestObserver("the");
        source.add(to);
        source.setFilterTerms(set("food"));
        pause(3 * 1000);
        assertTrue(to.getNTweets() > 0, "Expected getNTweets() to be > 0, was " + to.getNTweets());
        assertTrue(to.getNTweets() <= 10, "Expected getNTweets() to be <= 10, was " + to.getNTweets());
        int firstBunch = to.getNTweets();
        System.out.println("Now adding 'the'");
        source.setFilterTerms(set("food", "the"));
        pause(3 * 1000);
        assertTrue(to.getNTweets() > 0, "Expected getNTweets() to be > 0, was " + to.getNTweets());
        assertTrue(to.getNTweets() > firstBunch, "Expected getNTweets() to be < firstBunch (" + firstBunch + "), was " + to.getNTweets());
        assertTrue(to.getNTweets() <= 10, "Expected getNTweets() to be <= 10, was " + to.getNTweets());
    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private <E> Set<E> set(E ... p) {
        Set<E> ans = new HashSet<>();
        for (E a : p) {
            ans.add(a);
        }
        return ans;
    }
    private static class TestObserver implements Observer {
        private int nTweets = 0;
        private final String query;

        public TestObserver(String s){
            query = s;
        }

        @Override
        public void update(Observable o, Object arg) {
            nTweets ++;
            System.out.println("filter: "+ query + "\nstatus: " + ((Status)arg).getText());
            System.out.println("filter matches status? " + Filter.parse(query).matches((Status)arg));
            System.out.println("status contains queryString?: " + ((Status)arg).getText().contains(query));
        }

        public int getNTweets() {
            return nTweets;
        }
    }
}
