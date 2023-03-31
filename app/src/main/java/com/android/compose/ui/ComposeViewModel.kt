package com.android.compose.ui

import androidx.lifecycle.ViewModel
import com.android.compose.api.ComposeRepository
import com.android.compose.api.ComposeRepositoryImpl
import com.android.compose.data.HomeUIState
import com.android.library.utils.extension.AppContentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComposeViewModel(private val repository: ComposeRepository = ComposeRepositoryImpl()) :
    ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState(loading = true))
    val uiState: StateFlow<HomeUIState> = _uiState

    fun closeDetailScreen() {

    }

    fun setSelectedProduct(id: String,type:AppContentType) {

    }
}