package lotto.util;

import java.util.Arrays;
import java.util.List;

public class StringParser {
    public static List<String> stringToList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }
}
