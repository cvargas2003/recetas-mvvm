package com.cesar.recetasmvvm.presentation.state

import com.cesar.recetasmvvm.domain.model.Recipe

data class RecipeUiState(

    val isLoading: Boolean = false,

    val recipes: List<Recipe> = emptyList(),

    val error: String? = null
)