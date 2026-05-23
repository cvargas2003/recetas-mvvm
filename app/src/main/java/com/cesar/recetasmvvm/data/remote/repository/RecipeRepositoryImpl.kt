package com.cesar.recetasmvvm.data.repository
import com.cesar.recetasmvvm.data.remote.RetrofitClient
import com.cesar.recetasmvvm.domain.model.Recipe
import com.cesar.recetasmvvm.domain.model.RecipeStats
import com.cesar.recetasmvvm.domain.model.Review
import com.cesar.recetasmvvm.domain.repository.RecipeRepository

class RecipeRepositoryImpl : RecipeRepository {

    private val api = RetrofitClient.api

    override suspend fun getRecipes(): List<Recipe> {
        return api.getRecipes()
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return api.getRecipeById(id)
    }

    override suspend fun addReview(review: Review) {
        api.addReview(review)
    }

    override suspend fun getRecipeStats(
        id: Int
    ): RecipeStats {

        return RetrofitClient.api
            .getRecipeStats(id)
    }
    override suspend fun addRecipe(
        recipe: Recipe
    ) {

        RetrofitClient
            .api
            .addRecipe(recipe)
    }
}