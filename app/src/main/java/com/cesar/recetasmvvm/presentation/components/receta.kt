package com.cesar.recetasmvvm.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar.recetasmvvm.domain.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        shape = RoundedCornerShape(16.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = recipe.name,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = recipe.category
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "⭐ ${recipe.averageRating}"
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Preparada ${recipe.timesPrepared} veces"
            )
        }
    }
}