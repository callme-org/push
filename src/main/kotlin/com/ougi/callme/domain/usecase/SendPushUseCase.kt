package com.ougi.callme.domain.usecase

import com.ougi.callme.domain.mapper.mapToData
import com.ougi.callme.domain.model.Message
import com.ougi.callme.domain.model.SendResult
import com.ougi.callme.domain.repository.MessagingRepository

interface SendPushUseCase {

    suspend fun sendMessage(
        token: String,
        messageData: List<Message>
    ): SendResult

}

class SendPushUseCaseImpl(
    private val messagingRepository: MessagingRepository,
) : SendPushUseCase {

    override suspend fun sendMessage(
        token: String,
        messageData: List<Message>
    ): SendResult =
        messagingRepository.sendMessage(
            token = token,
            messageData = messageData.map(Message::mapToData)
        ).run {
            when (this) {
                "" -> SendResult.Success
                else -> SendResult.Failed.Unknown
            }
        }


}