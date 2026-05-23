package com.cesar.recetasmvvm.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cesar.recetasmvvm.presentation.components.RecipeCard
import com.cesar.recetasmvvm.presentation.viewmodel.RecipeViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: RecipeViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {

        viewModel.getRecipes()
    }
    Scaffold(

        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    navController.navigate(
                        "add_recipe"
                    )
                }
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when {

                uiState.isLoading -> {

                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.error != null -> {

                    Text(
                        text = uiState.error ?: "Unknown error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {

                        item {

                            Text(
                                text = "Chef Recipes",
                                style = MaterialTheme.typography.headlineMedium
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        items(uiState.recipes) { recipe ->

                            RecipeCard(

                                recipe = recipe,

                                onClick = {

                                    navController.navigate(
                                        "detail/${recipe.id}"
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}