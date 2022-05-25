package com.rnk0085.android.memo.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rnk0085.android.memo.database.memo.Memo
import com.rnk0085.android.memo.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Initial)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            memoRepository.getAllMemos()
                .catch { exception ->
                    _uiState.value = HomeUiState.Error

                    Log.d("HomeViewModel", "$exception")
                }
                .collect { memos ->
                    _uiState.value = HomeUiState.Success(memos)
                }
        }
    }
}

sealed class HomeUiState {
    object Initial : HomeUiState()
    object Loading : HomeUiState()
    data class Success(val memos: List<Memo>) : HomeUiState()
    object Error : HomeUiState()
}
