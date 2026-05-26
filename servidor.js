const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.use(express.json());

const recipes = [
  {
    id: 1,
    name: "Pasta Alfredo",
    description: "Pasta cremosa italiana",
    category: "Italiana",
    ingredients: [
      "200g pasta",
      "100ml crema de leche",
      "50g queso parmesano"
    ],
    steps: [
      "Hervir pasta",
      "Preparar salsa",
      "Mezclar ingredientes"
    ],
    preparationTime: 30,
    imageUrl: "",
    averageRating: 4.7,
    timesPrepared: 5
  },

  {
    id: 2,
    name: "Pollo al horno",
    description: "Pollo horneado con especias",
    category: "Carnes",
    ingredients: [
      "1 pollo",
      "Sal",
      "Pimienta"
    ],
    steps: [
      "Condimentar pollo",
      "Hornear 45 minutos"
    ],
    preparationTime: 45,
    imageUrl: "",
    averageRating: 4.5,
    timesPrepared: 3
  }
];

const reviews = [];

app.get("/recipes", (req, res) => {
  res.json(recipes);
});

app.get("/recipes/:id", (req, res) => {

  const recipe = recipes.find(
    r => r.id == req.params.id
  );

  res.json(recipe);
});

app.get("/recipes/:id/stats", (req, res) => {

  const recipeReviews = reviews.filter(
    r => r.recipeId == req.params.id
  );

  const totalReviews = recipeReviews.length;

  const averageRating =
    totalReviews > 0
      ? recipeReviews.reduce((acc, r) => acc + r.rating, 0) / totalReviews
      : 0;

  const totalPreparations = recipeReviews.reduce(
    (acc, r) => acc + r.timesCooked,
    0
  );

  res.json({
    averageRating,
    totalReviews,
    totalPreparations
  });
});

app.post("/reviews", (req, res) => {

  const review = req.body;

  reviews.push(review);

  const recipe = recipes.find(
    r => r.id === review.recipeId
  );

  if (recipe) {

    recipe.timesPrepared++;

    const recipeReviews = reviews.filter(
      r => r.recipeId === review.recipeId
    );

    const total = recipeReviews.reduce(
      (sum, r) => sum + r.rating,
      0
    );

    recipe.averageRating =
      total / recipeReviews.length;
  }

  res.status(201).json({
    message: "Review added successfully"
  });
});
app.post("/recipes", (req, res) => {

  const recipe = req.body;

  recipe.id = recipes.length + 1;

  recipe.averageRating = 0;
  recipe.timesPrepared = 0;

  recipes.push(recipe);

  res.status(201).json({
    message: "Recipe added successfully"
  });
});
app.get("/reviews/:recipeId", (req, res) => {

  const recipeId = parseInt(req.params.recipeId);

  const recipeReviews = reviews.filter(
    r => r.recipeId === recipeId
  );

  res.json(recipeReviews);
});
app.listen(3000, () => {
  console.log("Server running on port 3000");
});