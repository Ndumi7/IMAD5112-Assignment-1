package com.example.mealsuggestionapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the views by their IDs
        val loginButton = findViewById<Button>(R.id.loginButton)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val errorTextView = findViewById<TextView>(R.id.errorTextView)

        // Set up an OnClickListener for the login button
        loginButton?.setOnClickListener {
            // Get the text entered in the EditText (trim any extra spaces)
            val name = nameEditText.text.toString().trim()

            // Check if the name is not empty
            if (name.isNotEmpty()) {
                // If the name is valid, update the welcome message and hide the error message and show a toast

                Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT).show()
                errorTextView.visibility = View.GONE // Hide error message if name is valid

                // Create an Intent to navigate to MealSuggestionActivity
                val intent = Intent(this, MealSuggestionActivity::class.java)
                intent.putExtra("USERNAME", name) // Pass the username to the next screen
                startActivity(intent)
                finish() // Optionally close the login activity so the user can't go back

            } else {
                // If the name is empty, show the error message
                errorTextView.visibility = View.VISIBLE // Make error message visible
                errorTextView.text = "Please enter a valid username!" // Set the error message
                Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
