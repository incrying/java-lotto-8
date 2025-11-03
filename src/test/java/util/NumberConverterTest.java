package util;

import lotto.util.NumberConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberConverterTest {
    @DisplayName("문자열 리스트가 숫자 리스트로 변환된다.")
    @Test
    void convertToIntegerList() {
        List<String> strings = List.of("1","2","3","4","5","6");

        List<Integer> integers = NumberConverter.convertToIntegerList(strings);

        assertThat(integers).isEqualTo(List.of(1,2,3,4,5,6));
    }

    @DisplayName("공백을 제거한 문자열 리스트를 숫자 리스트로 변환한다.")
    @Test
    void convertToIntegerList_hasSpaces() {
        List<String> strings = List.of("1"," 2","3","4","       5","6");

        List<Integer> integers = NumberConverter.convertToIntegerList(strings);

        assertThat(integers).isEqualTo(List.of(1,2,3,4,5,6));
    }

    @DisplayName("문자열을 숫자로 변환한다.")
    @Test
    void convertToInteger() {
        String input = "1";

        Integer converted = NumberConverter.convertToInteger(input);

        assertThat(converted).isEqualTo(1);
    }
}
