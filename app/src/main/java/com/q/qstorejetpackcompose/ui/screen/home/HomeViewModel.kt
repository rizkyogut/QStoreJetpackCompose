package com.q.qstorejetpackcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.q.qstorejetpackcompose.data.PhoneRepository
import com.q.qstorejetpackcompose.data.model.PhoneList
import com.q.qstorejetpackcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PhoneRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<PhoneList>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<PhoneList>>>
        get() = _uiState

    fun getAllPhones() {
        viewModelScope.launch {
            repository.getAllPhones()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { dataPhone ->
                    _uiState.value = UiState.Success(dataPhone)
                }
        }
    }
}