import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
           int lengthA=(a==null)?0:a.length();
           int lengthB=(b==null)?0:b.length();
           return lengthA-lengthB;
        }
    }

    private static class StringComparator implements Comparator<String> {
        public int compare(String a, String b) {
            int lengthA=(a==null)?0:a.length();
            int lengthB=(b==null)?0:b.length();
            if (a != null) {
                if (b != null) {
                    return a.compareTo(b);
                }
            }
            return lengthA;
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());

        mad.addFirst("avn");
        mad.addFirst("tey");
        mad.addFirst("fury road");
       String max;
        max = mad.max(new StringComparator());
        System.out.println(max);
        mad.max();
        assertThat(mad.max()).isEqualTo("fury road");
        return;
    }

}
