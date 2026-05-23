package com.cesar.recetasmvvm.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cesar.recetasmvvm.data.repository.RecipeRepositoryImpl
import com.cesar.recetasmvvm.domain.model.Review
import kotlinx.coroutines.launch
import androidx.navigation.NavController

@Composable
fun ReviewScreen(
    recipeId: Int,
    navController: NavController
){

    var comment by remember {
        mutableStateOf("")
    }

    var rating by remember {
        mutableStateOf("")
    }

    var timesCooked by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Add Review",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = {
                comment = it
            },
            label = {
                Text("Comment")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = rating,
            onValueChange = {
                rating = it
            },
            label = {
                Text("Rating")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = timesCooked,
            onValueChange = {
                timesCooked = it
            },
            label = {
                Text("Times Cooked")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                scope.launch {

                    val review = Review(
                        id = 0,
                        recipeId = recipeId,
                        comment = comment,
                        rating = rating.toDouble(),
                        timesCooked = timesCooked.toInt(),
                        date = "2026-05-23"
                    )

                    RecipeRepositoryImpl()
                        .addReview(review)
                    navController.popBackStack()
                }
            }
        ) {

            Text("Save Review")
        }
    }
}