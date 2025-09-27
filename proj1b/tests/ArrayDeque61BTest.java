import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new ArrayDeque61B<>();

         lld1.addLast("back"); // after this call we expect: ["back"]
         System.out.println(lld1.toList());
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addLast("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back","front").inOrder();

     }

     @Test
     public void sizeTest() {
         Deque61B<String> lld1 = new ArrayDeque61B<>();

         lld1.addLast("back"); // after this call we expect: ["back"]
         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         lld1.addLast("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.size()).isEqualTo(3);
     }
    @Test
    public void getTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        lld1.addLast("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.get(7)).isEqualTo("middle");
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addFirst(1); // after this call we expect: ["back"]
        lld1.addFirst(2); // after this call we expect: ["middle", "back"]
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.removeFirst();
        // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly(3,2,1).inOrder();
     }
    @Test
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addFirst(1); // after this call we expect: ["back"]
        lld1.addFirst(2); // after this call we expect: ["middle", "back"]
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.removeLast();
        // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly(4,3,2).inOrder();
    }

    @Test
    public void resizeSmallTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

       for (int i=0;i<20;i++){
           lld1.addFirst(i);
       }



        // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1.size()).isEqualTo(20);
        System.out.println(lld1.get(2));
        assertThat(lld1.length()).isEqualTo(12);
    }
    @Test
    public void isEmptyTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addFirst(1); // after this call we expect: ["back"]
        lld1.addFirst(2); // after this call we expect: ["middle", "back"]
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isFalse();
    }




}
