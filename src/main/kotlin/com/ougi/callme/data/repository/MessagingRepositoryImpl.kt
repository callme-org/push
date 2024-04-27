package com.ougi.callme.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.ougi.callme.data.model.MessageData
import com.ougi.callme.domain.repository.MessagingRepository

class MessagingRepositoryImpl : MessagingRepository {

    override suspend fun sendMessage(
        token: String,
        messageData: List<MessageData>,
    ): String =
        Message.builder()
            .apply {
                messageData.forEach { data ->
                    putData(data.key, data.value)
                }
            }
            .setToken(token)
            .build()
            .run(FirebaseMessaging.getInstance()::send)

}