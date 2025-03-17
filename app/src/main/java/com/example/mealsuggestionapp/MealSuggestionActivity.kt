package com.example.mealsuggestionapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MealSuggestionActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_meal_suggestion)

        // Retrieve the username from the intent
        val username = intent.getStringExtra("USERNAME")

        // Find the TextView in the layout and set the welcome message
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        welcomeTextView.text = "Welcome, $username!" // Display welcome message

        // Define meal suggestions using variables
        val morning = "Pancakes with honey and a cup of coffee ☕"
        val midMorning = "A fruit smoothie or yogurt with granola 🍓"
        val afternoon = "Grilled chicken with rice and veggies 🍗"
        val midAfternoon = "A protein bar or a handful of nuts 🥜"
        val dinner = "Steak with mashed potatoes and salad 🥩"
        val afterDinner = "A light dessert like fruit salad or dark chocolate 🍫"

        // Assume we get the time of day somehow (You can change this to user input)
        val timeEditText = findViewById<EditText>(R.id.timeEditText)
        // Find the textView in the layout and set the suggestion message
        val mealSuggestionTextView = findViewById<TextView>(R.id.mealSuggestionTextView)
        val confirmButton = findViewById<Button>(R.id.confirmButton)

        confirmButton.setOnClickListener {
            val timeOfDay  = timeEditText.text.toString().trim()
            // Use `when` to select the right meal
            val mealSuggestion = when (timeOfDay) {
                "morning" -> morning
                "mid morning" -> midMorning
                "afternoon" -> afternoon
                "mid afternoon" -> midAfternoon
                "dinner" -> dinner
                "after dinner" -> afterDinner
                else -> "No meal suggestion available 😅"
            }
            // Display the meal suggestion
            mealSuggestionTextView.text = mealSuggestion
        }

    }
}