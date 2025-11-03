package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class NumberGenerator {
    public static List<Integer> generate(){
        List<Integer> numbers;
        numbers = Randoms.pickUniqueNumbersInRange(1,45,6);
        return numbers;
    }
}
