package com.q.qstorejetpackcompose.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.q.qstorejetpackcompose.data.PhoneRepository
import com.q.qstorejetpackcompose.ui.screen.detail.DetailPhoneViewModel
import com.q.qstorejetpackcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: PhoneRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailPhoneViewModel::class.java)) {
            return DetailPhoneViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}