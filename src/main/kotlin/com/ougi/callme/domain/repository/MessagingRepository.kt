package com.ougi.callme.domain.repository

import com.ougi.callme.data.model.MessageData

interface MessagingRepository {

    suspend fun sendMessage(
        token: String,
        messageData: List<MessageData>,
    ): String

}