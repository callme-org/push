package com.ougi.callme.di

import com.ougi.callme.data.repository.MessagingRepositoryImpl
import com.ougi.callme.domain.repository.MessagingRepository
import com.ougi.callme.domain.usecase.SendPushUseCase
import com.ougi.callme.domain.usecase.SendPushUseCaseImpl
import com.ougi.callme.presentation.firebase.FirebaseInitializer
import com.ougi.callme.presentation.firebase.FirebaseInitializerImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val dataModule = module {
    singleOf(::MessagingRepositoryImpl) { bind<MessagingRepository>() }
}

private val domainModule = module {
    singleOf(::SendPushUseCaseImpl) { bind<SendPushUseCase>() }
}

private val presentationModule = module {
    singleOf(::FirebaseInitializerImpl) { bind<FirebaseInitializer>() }
}

val appModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule,
    )
}