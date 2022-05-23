package com.rnk0085.android.memo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rnk0085.android.memo.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddMemoUiState>(AddMemoUiState.Initial)
    val uiState: StateFlow<AddMemoUiState> = _uiState

    fun addNewMemo(memoTitle: String, memoContent: String) {
        viewModelScope.launch {
            try {
                if (memoTitle == "") memoRepository.insert("タイトル無し", memoContent)
                else memoRepository.insert(memoTitle, memoContent)
                _uiState.value = AddMemoUiState.Success
            } catch (e: Exception) {
                _uiState.value = AddMemoUiState.Failure
            }
        }
    }

    fun errorCancelled() {
        _uiState.value = AddMemoUiState.Initial
    }

    fun isEntryValid(memoTitle: String, memoContent: String) : Boolean {
        if (memoTitle.isBlank() && memoContent.isBlank()) return false

        return true
    }
}

sealed class AddMemoUiState {
    object Initial : AddMemoUiState()
    object Success : AddMemoUiState()
    object Failure : AddMemoUiState()
}
