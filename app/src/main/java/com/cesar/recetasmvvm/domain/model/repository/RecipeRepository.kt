package com.cesar.recetasmvvm.domain.repository

import com.cesar.recetasmvvm.domain.model.Recipe
import com.cesar.recetasmvvm.domain.model.RecipeStats
import com.cesar.recetasmvvm.domain.model.Review

interface RecipeRepository {

    suspend fun getRecipes(): List<Recipe>

    suspend fun getRecipeById(id: Int): Recipe

    suspend fun getRecipeStats(id: Int): RecipeStats

    suspend fun addReview(review: Review)
}