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

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            memoRepository.getAllMemos()
                .catch { exception ->
                    _uiState.update {
                        it.copy(isError = true)
                    }

                    Log.d("HomeViewModel", "$exception")
                }
                .collect { memos ->
                    _uiState.update {
                        it.copy(memos = memos)
                    }
                }
        }
    }
}

data class HomeUiState(
    val memos: List<Memo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
