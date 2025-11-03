package lotto.validator;

import lotto.exception.ErrorMessage;
import lotto.util.NumberConverter;
import lotto.util.StringParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidator {

    public void validateBonusNumber(String number, List<Integer> lotto) {
        validateNotBlank(number);
        validateNumeric(number);
        validatePositiveNumber(number);
        validateRange(number);
        validateDuplicateNumber(number, lotto);
    }

    public void validateInputNumbers(String numbers) {
        validateNotBlank(numbers);
        List<String> array = StringParser.stringToList(numbers);
        validateLottoCount(array);

        array.forEach(this::validateNumeric);
        array.forEach(this::validatePositiveNumber);
        array.forEach(this::validateRange);

        validateNoDuplicate(array);
    }

    public void validateAmountInput(String amount) {
        validateNotBlank(amount);
        validateNumeric(amount);
        validatePositiveNumber(amount);
        validateMultipleOfThousand(NumberConverter.convertToInteger(amount));
    }

    public void validateDuplicateNumber(String number, List<Integer> lotto) {
        if (lotto.contains(Integer.parseInt(number))) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    private void validateLottoCount(List<String> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }
    }

    public void validatePositiveNumber(String input) {
        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSITIVE_NUMBER.getMessage());
        }
    }

    public void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER.getMessage());
        }
    }

    public void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.IS_BLANK.getMessage());
        }
    }

    public void validateMultipleOfThousand(int input) {
        if (input % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT.getMessage());
        }
    }

    private void validateRange(String input) {
        int num = Integer.parseInt(input);
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE.getMessage());
        }
    }

    private void validateNoDuplicate(List<String> numbers) {
        Set<String> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER.getMessage());
        }
    }
}
