package com.android.compose.ui

import androidx.lifecycle.ViewModel
import com.android.compose.api.ComposeRepository
import com.android.compose.api.ComposeRepositoryImpl
import com.android.compose.data.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComposeViewModel(private val repository: ComposeRepository = ComposeRepositoryImpl()) :
    ViewModel() {
    private val _uiState = MutableStateFlow(UIState(loading = true))
    val uiState: StateFlow<UIState> = _uiState
}