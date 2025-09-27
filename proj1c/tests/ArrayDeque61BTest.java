import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class ArrayDeque61BTest<T> extends ArrayDeque61B<T> {
    @Test
    @DisplayName("123")
    public void ArrayTest(){

        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
//        for (String s : lld1) {
//            System.out.println(s);
//        }
        Iterator<String> iterator = lld1.iterator(); // 需要 iterator() 方法
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
  }
}
