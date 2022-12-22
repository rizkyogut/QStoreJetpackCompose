package com.q.qstorejetpackcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.q.qstorejetpackcompose.data.PhoneRepository
import com.q.qstorejetpackcompose.data.model.PhoneList
import com.q.qstorejetpackcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPhoneViewModel(
    private val repository: PhoneRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<PhoneList>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<PhoneList>>
        get() = _uiState

    fun getPhoneById(phoneId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPhoneById(phoneId))
        }
    }
}