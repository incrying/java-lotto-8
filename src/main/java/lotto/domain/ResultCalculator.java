package lotto.domain;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResultCalculator {

    public Map<Rank, Integer> calculateResults(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = winningLotto.countMatch(lotto);
            boolean bonusMatch = winningLotto.isBonusMatch(lotto);
            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            results.put(rank, results.get(rank) + 1);
        }

        return results;
    }

    public double calculateProfitRate(Map<Rank, Integer> results, int purchaseAmount) {
        long totalPrize = results.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().getPrize() * e.getValue())
                .sum();

        return ((double) totalPrize / purchaseAmount) * 100;
    }
}
