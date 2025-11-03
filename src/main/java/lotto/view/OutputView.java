package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public  void printError(String errorMessage){
        System.out.println("[ERROR]" + errorMessage);
    }

    public void printLottos(int count, List<Lotto> lottos){
        System.out.println(count + "개를 구매했습니다.");
        lottos.forEach(lotto ->
                System.out.println(lotto.getNumbers().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ", "[", "]"))));
    }

    public void printResult(Map<Rank, Integer> results, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + results.get(Rank.THREE) + "개");
        System.out.println("4개 일치 (50,000원) - " + results.get(Rank.FOUR) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + results.get(Rank.FIVE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + results.get(Rank.FIVE_BONUS) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + results.get(Rank.SIX) + "개");
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

}
