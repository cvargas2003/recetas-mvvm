package com.cesar.recetasmvvm.domain.model

data class Review(
    val id: Int,
    val recipeId: Int,
    val comment: String,
    val rating: Double,
    val timesCooked: Int,
    val date: String
)