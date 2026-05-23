package com.cesar.recetasmvvm.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cesar.recetasmvvm.data.repository.RecipeRepositoryImpl
import com.cesar.recetasmvvm.domain.model.Recipe
import kotlinx.coroutines.launch
import androidx.compose.material3.Button
import androidx.navigation.NavController
import com.cesar.recetasmvvm.domain.model.RecipeStats

@Composable
fun DetailScreen(
    recipeId: Int,
    navController: NavController
){

    var recipe by remember {
        mutableStateOf<Recipe?>(null)
    }
    var stats by remember {
        mutableStateOf<RecipeStats?>(null)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        scope.launch {

            recipe = RecipeRepositoryImpl()
                .getRecipeById(recipeId)
            stats = RecipeRepositoryImpl()
                .getRecipeStats(recipeId)
        }
    }

    recipe?.let {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            item {

                Text(
                    text = it.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = it.description)
                Spacer(modifier = Modifier.height(16.dp))

                stats?.let { recipeStats ->

                    Text(
                        text = "⭐ Average Rating: ${recipeStats.averageRating}"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "📝 Total Reviews: ${recipeStats.totalReviews}"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "👨‍🍳 Total Preparations: ${recipeStats.totalPreparations}"
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Category: ${it.category}"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Preparation Time: ${it.preparationTime} min"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(it.ingredients) { ingredient ->

                Text(text = "• $ingredient")
            }

            item {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Steps",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(it.steps) { step ->

                Text(text = "• $step")
            }
            item {

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {

                        navController.navigate(
                            "review/$recipeId"
                        )
                    }
                ) {

                    Text("Add Review")
                }
            }
        }
    }
}