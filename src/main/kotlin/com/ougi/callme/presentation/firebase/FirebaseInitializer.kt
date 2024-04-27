package com.ougi.callme.presentation.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream


interface FirebaseInitializer {

    fun initialize()

}

class FirebaseInitializerImpl : FirebaseInitializer {

    override fun initialize() {
        val serviceAccount = FileInputStream("./firebaseServiceAccountKey.json")

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)
    }
}