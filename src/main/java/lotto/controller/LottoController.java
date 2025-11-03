package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.ResultCalculator;
import lotto.domain.WinningLotto;
import lotto.util.NumberConverter;
import lotto.util.NumberGenerator;
import lotto.util.StringParser;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;


public class LottoController {

    InputView inputView;
    OutputView outputView;
    InputValidator validator;

    public LottoController(InputView inputView, OutputView outputView, InputValidator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = validator;
    }
    public void run(){
        int input = getAmountInput();
        int count = amountToCount(input);
        List<Lotto> lottos = createLotto(count);
        outputView.printLottos(count, lottos);

        List<Integer> winningNumbers = getNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        ResultCalculator calculator = new ResultCalculator();
        Map<Rank, Integer> results = calculator.calculateResults(lottos, winningLotto);
        double profitRate = calculator.calculateProfitRate(results, input);

        outputView.printResult(results, profitRate);
    }

    public List<Lotto> createLotto(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Lotto(NumberGenerator.generate()))
                .toList();
    }

    public int amountToCount(int input){
        return input/1000;
    }

    public int getBonusNumber(List<Integer> lotto){
        return doLoop(() ->{
            String input = inputView.inputBonusNumber();
            validator.validateBonusNumber(input, lotto);
            return Integer.parseInt(input);
        });
    }

    public List<Integer> getNumbers(){
        return doLoop(() -> {
            String input = inputView.inputLottoNumbers();
            validator.validateInputNumbers(input);
            List<String> inputs = StringParser.stringToList(input);
            List<Integer> lotto = NumberConverter.convertToIntegerList(inputs);
            return lotto;
        });
    }

    public int getAmountInput(){
        return doLoop(() -> {
            String input = inputView.inputAmount();
            validator.validateAmountInput(input);
            return Integer.parseInt(input);
        });
    }

    private <T> T doLoop(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}
