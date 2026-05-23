package com.cesar.recetasmvvm.data.remote

import com.cesar.recetasmvvm.domain.model.Recipe
import com.cesar.recetasmvvm.domain.model.RecipeStats
import com.cesar.recetasmvvm.domain.model.Review
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RecipeApiService {

    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("recipes/{id}")
    suspend fun getRecipeById(
        @Path("id") id: Int
    ): Recipe

    @GET("recipes/{id}/stats")
    suspend fun getRecipeStats(
        @Path("id") id: Int
    ): RecipeStats

    @POST("reviews")
    suspend fun addReview(
        @Body review: Review
    )
    @POST("recipes")
    suspend fun addRecipe(
        @Body recipe: Recipe
    )
}