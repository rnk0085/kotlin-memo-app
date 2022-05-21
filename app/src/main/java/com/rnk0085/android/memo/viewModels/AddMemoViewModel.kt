package com.rnk0085.android.memo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rnk0085.android.memo.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddMemoViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    fun insertMemo(memoTitle: String, memoContent: String) {
        viewModelScope.launch {
            try {
                memoRepository.insert(memoTitle, memoContent)
            } catch (e: Exception) {
                // TODO：エラー処理
            }
        }
    }

    fun isEntryValid(memoTitle: String, memoContent: String) : Boolean {
        if (memoTitle.isBlank() || memoContent.isBlank()) return false

        return true
    }
}
