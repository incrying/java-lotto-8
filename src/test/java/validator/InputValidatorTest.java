package validator;

import lotto.exception.ErrorMessage;
import lotto.validator.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    private InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();
    }

    @Test
    @DisplayName("정상 입력일 경우 예외가 발생하지 않는다.")
    void validateInputNumbers_success() {
        String input = "1, 2, 3, 4, 5, 6";
        assertThatCode(() -> validator.validateInputNumbers(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력에 중복된 숫자가 있으면 예외 발생")
    void validateInputNumbers_duplicate() {
        String input = "1, 2, 3, 3, 4, 5";
        assertThatThrownBy(() -> validator.validateInputNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("입력 숫자가 6개가 아니면 예외 발생")
    void validateInputNumbers_wrongCount() {
        String input = "1, 2, 3, 4, 5";
        assertThatThrownBy(() -> validator.validateInputNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
    }

    @Test
    @DisplayName("입력에 숫자가 아닌 값이 있으면 예외 발생")
    void validateInputNumbers_notNumber() {
        String input = "1, 2, a, 4, 5, 6";
        assertThatThrownBy(() -> validator.validateInputNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER.getMessage());
    }

    @Test
    @DisplayName("입력 숫자가 1~45 범위를 벗어나면 예외 발생")
    void validateInputNumbers_outOfRange() {
        String input = "1, 2, 3, 4, 5, 46";
        assertThatThrownBy(() -> validator.validateInputNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_RANGE.getMessage());
    }

    @Test
    @DisplayName("공백만 입력하면 예외 발생")
    void validateInputNumbers_blank() {
        String input = "   ";
        assertThatThrownBy(() -> validator.validateInputNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.IS_BLANK.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 중복되면 예외 발생")
    void validateBonusNumber_duplicate() {
        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6);
        String bonus = "3";
        assertThatThrownBy(() -> validator.validateBonusNumber(bonus, lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외 발생")
    void validateBonusNumber_outOfRange() {
        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6);
        String bonus = "50";
        assertThatThrownBy(() -> validator.validateBonusNumber(bonus, lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_RANGE.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 정상일 경우 예외 없음")
    void validateBonusNumber_success() {
        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6);
        String bonus = "7";
        assertThatCode(() -> validator.validateBonusNumber(bonus, lotto))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("금액이 1000원 단위가 아니면 예외 발생")
    void validateAmountInput_invalidUnit() {
        String input = "1500";
        assertThatThrownBy(() -> validator.validateAmountInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("금액이 정상(1000원 단위)이면 예외 없음")
    void validateAmountInput_success() {
        String input = "3000";
        assertThatCode(() -> validator.validateAmountInput(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("금액 입력이 공백이면 예외 발생")
    void validateAmountInput_blank() {
        String input = " ";
        assertThatThrownBy(() -> validator.validateAmountInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.IS_BLANK.getMessage());
    }

    @Test
    @DisplayName("금액 입력이 숫자가 아니면 예외 발생")
    void validateAmountInput_notNumber() {
        String input = "abc";
        assertThatThrownBy(() -> validator.validateAmountInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER.getMessage());
    }

    @Test
    @DisplayName("0 또는 음수를 입력하면 예외 발생")
    void validatePositiveNumber_negative() {
        assertThatThrownBy(() -> validator.validatePositiveNumber("-5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_POSITIVE_NUMBER.getMessage());
    }
}
