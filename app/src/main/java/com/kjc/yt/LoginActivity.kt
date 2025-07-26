package com.kjc.yt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is already logged in
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        // Retrieve UI elements
        val usernameField: EditText = findViewById(R.id.enter_username)
        val passwordField: EditText = findViewById(R.id.enter_password)
        val loginButton: Button = findViewById(R.id.login)
        val registerLabel: TextView = findViewById(R.id.you_have_account)

        // Login button logic
        loginButton.setOnClickListener {
            val enteredName = usernameField.text.toString().trim()
            val enteredPassword = passwordField.text.toString().trim()

            // Retrieve saved credentials from SharedPreferences
            val savedName = sharedPreferences.getString("name", null)
            val savedPassword = sharedPreferences.getString("password", null)

            if (enteredName.isBlank() || enteredPassword.isBlank()) {
                Toast.makeText(this, "Please enter both name and password.", Toast.LENGTH_SHORT).show()
            } else if (savedName.isNullOrEmpty() || savedPassword.isNullOrEmpty()) {
                Toast.makeText(this, "No registered account found. Please register first.", Toast.LENGTH_SHORT).show()
            } else if (enteredName == savedName && enteredPassword == savedPassword) {
                // Save login status
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        // "Don't have an account? Register" label logic
        registerLabel.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}
