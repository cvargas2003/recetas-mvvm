package com.cesar.recetasmvvm.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cesar.recetasmvvm.data.repository.RecipeRepositoryImpl
import com.cesar.recetasmvvm.domain.model.Recipe
import kotlinx.coroutines.launch

@Composable
fun AddRecipeScreen(
    navController: NavController
) {

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }
    var preparationTime by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Add Recipe",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Recipe Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text("Description")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = category,
            onValueChange = {
                category = it
            },
            label = {
                Text("Category")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = ingredients,
            onValueChange = {
                ingredients = it
            },
            label = {
                Text("Ingredients (comma separated)")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = steps,
            onValueChange = {
                steps = it
            },
            label = {
                Text("Steps (comma separated)")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = preparationTime,
            onValueChange = {
                preparationTime = it
            },
            label = {
                Text("Preparation Time")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                scope.launch {

                    val recipe = Recipe(
                        id = 0,
                        name = name,
                        description = description,
                        category = category,
                        ingredients = ingredients.split(","),
                        steps = steps.split(","),
                        preparationTime = preparationTime.toInt(),
                        imageUrl = "",
                        averageRating = 0.0,
                        timesPrepared = 0
                    )

                    RecipeRepositoryImpl()
                        .addRecipe(recipe)

                    navController.popBackStack()
                }
            }
        ) {

            Text("Save Recipe")
        }
    }
}