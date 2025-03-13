package com.example.mealsuggestionapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the views by their IDs
        val loginButton = findViewById<Button>(R.id.loginButton)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val errorTextView = findViewById<TextView>(R.id.errorTextView)

        // Set up an OnClickListener for the login button
        loginButton?.setOnClickListener {
            // Get the text entered in the EditText (trim any extra spaces)
            val name = nameEditText.text.toString().trim()

            // Check if the name is not empty
            if (name.isNotEmpty()) {
                // If the name is valid, update the welcome message and hide the error message
                welcomeTextView.text = "Welcome, $name!"
                errorTextView.visibility = View.GONE // Hide error message if name is valid
            } else {
                // If the name is empty, show the error message
                errorTextView.visibility = View.VISIBLE // Make error message visible
                errorTextView.text = "Please enter a valid username!" // Set the error message
            }
        }
    }
}
