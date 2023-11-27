package umc.apiPayload.exception.handler;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
