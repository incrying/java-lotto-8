package util;

import java.util.List;

import lotto.util.StringParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringParserTest {

    @DisplayName("문자열이 콤마를 기준으로 파싱된다.")
    @Test
    void parseInput() {
        String input = "1,2,3,4,5,6";

        List<String> parsedInput = StringParser.stringToList(input);

        assertThat(parsedInput).isEqualTo(List.of("1","2","3","4","5","6"));
    }

}