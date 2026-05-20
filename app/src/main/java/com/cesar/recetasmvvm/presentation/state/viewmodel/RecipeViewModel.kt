package com.cesar.recetasmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesar.recetasmvvm.data.repository.RecipeRepositoryImpl
import com.cesar.recetasmvvm.presentation.state.RecipeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val repository = RecipeRepositoryImpl()

    private val _uiState = MutableStateFlow(RecipeUiState())

    val uiState: StateFlow<RecipeUiState> = _uiState

    init {
        getRecipes()
    }

    fun getRecipes() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true
            )

            try {

                val recipes = repository.getRecipes()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    recipes = recipes
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}