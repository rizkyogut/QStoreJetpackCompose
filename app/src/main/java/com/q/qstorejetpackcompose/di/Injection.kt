package com.q.qstorejetpackcompose.di

import com.q.qstorejetpackcompose.data.PhoneRepository

object Injection {
    fun provideRepository(): PhoneRepository {
        return PhoneRepository.getInstance()
    }
}