package util;

import lotto.util.NumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberGeneratorTest {
    @DisplayName("숫자 6개를 생성한다.")
    @Test
    void GenerateTest(){
        List<Integer> inputList;

        inputList = NumberGenerator.generate();
        int output = inputList.size();

        assertThat(output).isEqualTo(6);
    }
}
