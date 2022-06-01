package com.rnk0085.android.memo.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rnk0085.android.memo.database.entity.MemoEntity
import com.rnk0085.android.memo.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Initial)
    val uiState: StateFlow<DetailUiState> = _uiState

    fun getMemo(id: Int) {
        viewModelScope.launch {
            memoRepository.getMemo(id)
                .catch { exception ->
                    _uiState.value = DetailUiState.Error
                    Log.e("DetailViewModel", "$exception")
                }
                .collect { memo ->
                    _uiState.value = DetailUiState.Success(memo)
                }
        }
    }
}

sealed class DetailUiState {
    object Initial : DetailUiState()
    data class Success(val memo: MemoEntity) : DetailUiState()
    object Error : DetailUiState()
}
