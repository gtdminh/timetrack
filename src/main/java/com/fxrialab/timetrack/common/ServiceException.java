package com.fxrialab.timetrack.common;

public class ServiceException extends Exception {

    public enum SERVICE_EXCEPTION_CODE {
        UNEXPECTED_ISSUE, SEND_MAIL_FAIL, USER_EXISTING,
        NO_CONFIRMATION_CODE, NO_WAITING_FOR_CONFIRMATION, USER_HAS_BEEN_ACTIVATED,
        INVALID_CREATE_PASSWORD,
        RESPONSE_CONTAIN_INVALID_CHARACTERS, RECAPTCHA_NOT_SUCCESSFULLY_VALIDATED, UNKNOWN_HOST_EXCEPTION;

        public static ServiceException.SERVICE_EXCEPTION_CODE parse(final String status) {
            for (final ServiceException.SERVICE_EXCEPTION_CODE s : ServiceException.SERVICE_EXCEPTION_CODE.values()) {
                if (s.toString() == status) return s;
            }

            return null;
        }
    }


    private SERVICE_EXCEPTION_CODE exceptionCode;

    public ServiceException(SERVICE_EXCEPTION_CODE code) {
        this.exceptionCode = code;
    }


    public ServiceException(SERVICE_EXCEPTION_CODE code, String message) {
        super(message);
        this.exceptionCode = code;
    }

    public ServiceException(SERVICE_EXCEPTION_CODE code, Throwable cause) {
        super(cause);
        this.exceptionCode = code;
    }

    public ServiceException(SERVICE_EXCEPTION_CODE code, String message, Throwable cause) {
        super(message, cause);
        this.exceptionCode = code;
    }

    public SERVICE_EXCEPTION_CODE getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(SERVICE_EXCEPTION_CODE exceptionCode) {
        this.exceptionCode = exceptionCode;
    }


}
