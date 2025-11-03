package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public int countMatch(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
    }

    public boolean isBonusMatch(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}

