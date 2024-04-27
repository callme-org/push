package com.ougi.callme.domain.model

sealed interface SendResult {

    data object Success : SendResult

    sealed interface Failed : SendResult {
        data object Unknown : Failed
    }

}