package com.example.mealsuggestionapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        welcomeTextView.text = "Welcome, $username \uD83D\uDC4B!" // Display welcome message

        // Define meal suggestions using variables
        val morning = "Breakfast: Toasted bread \uD83C\uDF5E with  Butter \uD83E\uDDC8 and  Honey \uD83C\uDF6F and a cup of coffee ☕"
        val midMorning = "Snack: A fruit smoothie \uD83C\uDF53 or yogurt with granola \uD83E\uDD63"
        val afternoon = "Lunch: Grilled chicken \uD83C\uDF57 with rice and veggies \uD83C\uDF5A \uD83E\uDD66"
        val midAfternoon = "Snack: A protein \uD83C\uDF6B bar or a handful of nuts 🥜"
        val evening = "Steak \uD83E\uDD69 with mashed potatoes and salad \uD83E\uDD54 \uD83E\uDD57"
        val afterDinner = "A light bowl of ice cream \uD83C\uDF68 or a cheesecake \uD83C\uDF70 "

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
        val enjoyMealText = findViewById<TextView>(R.id.enjoyMealText)

        var mealConfirmed = false  // Track if a meal is already suggested


        resetButton.setOnClickListener {
            // Set the mealConfirmed flag to false, indicating no meal has been confirmed yet
            mealConfirmed = false
            // Clear the text input in the timeEditText (EditText field for time period)
            timeEditText.text.clear()
            // Hide the meal error text, in case it was previously displayed due to an invalid time input
            mealErrorText.visibility = View.GONE
            // Clear the text displayed in the meal suggestion TextView
            mealSuggestionTextView.text = ""
            // Hide the "Enjoy your meal" text, as it's only shown after confirming a meal suggestion
            enjoyMealText.visibility = View.GONE
        }


        confirmButton.setOnClickListener {
            val timeOfDay  = timeEditText.text.toString().trim()

            // ❌ If input is empty, show error and exit
            if (timeOfDay.isEmpty()) {
                mealErrorText.visibility = View.VISIBLE
                mealErrorText.text = "Please enter a valid period!"
                mealSuggestionTextView.text = "" // Clear previous meal
                enjoyMealText.visibility = View.GONE // Hide "Enjoy your meal!"
                mealConfirmed = false // Reset confirmation
                return@setOnClickListener
            }

            // ✅ If meal is already confirmed, show "Enjoy your meal!" and return
            if (mealConfirmed) {
                enjoyMealText.text = "Enjoy your meal 🍽️!"
                enjoyMealText.visibility = View.VISIBLE
                Toast.makeText(this, "Enjoy your meal! 🍽️", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔄 Reset state before selecting a new meal
            mealConfirmed = false
            enjoyMealText.visibility = View.GONE
            mealErrorText.visibility = View.GONE
            mealSuggestionTextView.text = ""  // Clear previous meal suggestion

            // Select a meal based on input
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
                // ✅ Show meal suggestion
                mealSuggestionTextView.text = mealSuggestion
                mealConfirmed = true  // Now the next click will trigger "Enjoy your meal!"
            } else {
                // ❌ Show error if input is invalid
                mealErrorText.visibility = View.VISIBLE
                mealErrorText.text = "Please make sure you entered a valid period!"
                mealSuggestionTextView.text = "" // Clear previous suggestion
            }

        }
    }
}