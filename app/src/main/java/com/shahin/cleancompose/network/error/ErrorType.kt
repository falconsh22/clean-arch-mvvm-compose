package com.shahin.cleancompose.network.error


enum class ErrorType(val internalCode: Int) {
    ExceptionQUOTA (4),
    ExceptionItemsLLimitExceeded (100),
    OAuthExceptionPermission (200),
    OAuthExceptionInvalidToken (300),
    ParameterException (500),
    MissingParameterException (501),
    InvalidQueryException (600),
    Exception (700),
    DataException (800),
    IndividualAccountChangedNotAllowedException (901),
    Unknown (0)
}
