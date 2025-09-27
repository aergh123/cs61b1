import org.junit.jupiter.api.*;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class test {
    @Test
    @Order(0)
    @DisplayName("Test makeDice correctness1")
    public void testMakeDice() {
        int[] dice = JavaExercises.makeDice();
        assertThat(dice).isNotNull();
        assertThat(dice.length).isEqualTo(6);
        for (int i = 0; i < 6; i++) {
            assertThat(dice[i]).isEqualTo(i + 1);
        }
    }

    @Test
    @Order(1)
    @DisplayName("Test takeOrder correctness")
    public void testTakeOrder(){
             String[] food1=JavaExercises.takeOrder("Ergun");
             assertThat(food1).hasLength(4);
             assertThat(food1).isNotNull();

    }
}
