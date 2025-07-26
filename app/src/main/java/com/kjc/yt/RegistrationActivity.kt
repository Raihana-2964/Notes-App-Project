package com.kjc.yt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Retrieve UI elements
        val nameField: EditText = findViewById(R.id.name)
        val passwordField: EditText = findViewById(R.id.password)
        val confirmPasswordField: EditText = findViewById(R.id.confirm)
        val emailField: EditText = findViewById(R.id.email)
        val registerButton: Button = findViewById(R.id.register_button)
        val alreadyHaveAccount: TextView = findViewById(R.id.already_have_account)

        // Access SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // Register button logic
        registerButton.setOnClickListener {
            val name = nameField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val confirmPassword = confirmPasswordField.text.toString().trim()
            val email = emailField.text.toString().trim()

            if (name.isNotBlank() && password.isNotBlank() && password == confirmPassword) {
                // Save name and password to SharedPreferences
                sharedPreferences.edit().apply {
                    putString("name", name)
                    putString("password", password)
                    putString("email", email)
                    apply()
                }

                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()

                // Navigate to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please check your inputs!", Toast.LENGTH_SHORT).show()
            }
        }

        // "Already have an account? Login" label logic
        alreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
