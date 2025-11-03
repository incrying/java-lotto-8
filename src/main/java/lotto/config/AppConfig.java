package lotto.config;

import lotto.controller.LottoController;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import javax.xml.validation.Validator;

public class AppConfig {
    public InputView inputView(){
        return new InputView();
    }

    public OutputView outputView(){
        return new OutputView();
    }

    public InputValidator inputValidator(){return new InputValidator();}
}
