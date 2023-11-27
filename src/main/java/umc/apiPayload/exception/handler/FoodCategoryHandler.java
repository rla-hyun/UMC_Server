package umc.apiPayload.exception.handler;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
