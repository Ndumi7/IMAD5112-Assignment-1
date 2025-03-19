package com.example.mealsuggestionapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
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
        val morning = "Breakfast: Pancakes \uD83E\uDD5E with honey and a cup of coffee ☕"
        val midMorning = "Snack: A fruit smoothie \uD83C\uDF53 or yogurt with granola \uD83E\uDD63"
        val afternoon = "Lunch: Grilled chicken \uD83C\uDF57 with rice and veggies \uD83C\uDF5A \uD83E\uDD66"
        val midAfternoon = "Snack: A protein \uD83C\uDF6B bar or a handful of nuts 🥜"
        val evening = "Steak \uD83E\uDD69 with mashed potatoes and salad \uD83E\uDD54 \uD83E\uDD57"
        val afterDinner = "A light bowl of ice cream \uD83C\uDF68"

        // Assume we get the time of day somehow (You can change this to user input)
        val timeEditText = findViewById<EditText>(R.id.timeEditText)
        // Notifies user if period is invalid
        val mealErrorText = findViewById<TextView>(R.id.mealErrorText)
        // Find the textView in the layout and set the suggestion message
        val mealSuggestionTextView = findViewById<TextView>(R.id.mealSuggestionTextView)
        // Refreshes the whole page
        val resetButton = findViewById<Button>(R.id.resetButton)
        // confirms the suggested option
        val confirmButton = findViewById<Button>(R.id.confirmButton)

        resetButton.setOnClickListener {
            timeEditText.text.clear()
            mealSuggestionTextView.text = ""
        }


        confirmButton.setOnClickListener {
            val timeOfDay  = timeEditText.text.toString().trim()
            // Use `when` to select the right meal
            val mealSuggestion = when (timeOfDay) {
                "Morning" -> morning
                "Mid morning" -> midMorning
                "Afternoon" -> afternoon
                "Mid afternoon" -> midAfternoon
                "Evening" -> evening
                "After dinner" -> afterDinner
                else -> null
            }
            if (mealSuggestion != null) {
                // Hide error message and show the valid meal suggestion
                mealErrorText.visibility = View.GONE
                mealSuggestionTextView.text = mealSuggestion
            } else {
                // Show error message if input is invalid
                mealErrorText.visibility = View.VISIBLE
                mealErrorText.text = "Please make sure you entered a valid period!"
                mealSuggestionTextView.text = "" // Clear previous suggestion
            }
            // Display the meal suggestion
            mealSuggestionTextView.text = mealSuggestion
        }
    }
}