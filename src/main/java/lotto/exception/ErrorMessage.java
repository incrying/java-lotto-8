package lotto.exception;

public enum ErrorMessage {

    NOT_NUMBER("입력이 숫자가 아닙니다."),
    IS_BLANK("공백을 입력하였습니다."),
    INVALID_AMOUNT("1000원 단위로 입력해주세요."),
    NOT_POSITIVE_NUMBER("자연수를 입력해주세요."),

    INVALID_NUMBER_COUNT("로또 개수는 6개여야 합니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복일 수 없습니다."),
    DUPLICATE_NUMBER("중복된 숫자가 있습니다."),
    INVALID_RANGE("로또 번호는 1~45 사이여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
