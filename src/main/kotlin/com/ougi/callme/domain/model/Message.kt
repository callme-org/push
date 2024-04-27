package com.ougi.callme.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Message(
    @SerialName("key") val key: String,
    @SerialName("value") val value: String,
)