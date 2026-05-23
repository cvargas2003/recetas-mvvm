package com.cesar.recetasmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cesar.recetasmvvm.presentation.screens.DetailScreen
import com.cesar.recetasmvvm.presentation.screens.HomeScreen
import com.cesar.recetasmvvm.presentation.screens.ReviewScreen
import androidx.navigation.NavController
import com.cesar.recetasmvvm.presentation.screens.AddRecipeScreen
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            HomeScreen(navController)
        }

        composable(
            route = "detail/{recipeId}",

            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val recipeId =
                backStackEntry.arguments?.getInt("recipeId") ?: 0

            DetailScreen(
                recipeId = recipeId,
                navController = navController
            )
        }
        composable("add_recipe") {

            AddRecipeScreen(navController)
        }
        composable(
            route = "review/{recipeId}",

            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val recipeId =
                backStackEntry.arguments?.getInt("recipeId") ?: 0

            ReviewScreen(
                recipeId = recipeId,
                navController = navController
            )

        }
    }
}