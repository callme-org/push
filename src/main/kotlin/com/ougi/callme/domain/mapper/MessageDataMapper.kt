package com.ougi.callme.domain.mapper

import com.ougi.callme.data.model.MessageData
import com.ougi.callme.domain.model.Message

fun Message.mapToData() =
    MessageData(
        key = key,
        value = value
    )