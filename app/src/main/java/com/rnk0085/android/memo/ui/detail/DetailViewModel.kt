package com.rnk0085.android.memo.ui.detail

class DetailViewModel {
}

sealed class DetailUiState {
    object Initial : DetailUiState()
    object Success : DetailUiState()
    object Error : DetailUiState()
}
