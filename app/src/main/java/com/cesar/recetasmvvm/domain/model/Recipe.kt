package com.cesar.recetasmvvm.domain.model

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val preparationTime: Int,
    val imageUrl: String,
    val averageRating: Double,
    val timesPrepared: Int
)