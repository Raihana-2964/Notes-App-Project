package com.kjc.yt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class NavbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)

        // Access SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("name", null)
        val email = sharedPreferences.getString("email", null)

        // Display username and email
        val welcomeTextView: TextView = findViewById(R.id.welcomeUser)
        val emailTextView: TextView = findViewById(R.id.userEmail)

        if (username != null && email != null) {
            welcomeTextView.text = "Username: $username"
            emailTextView.text = "Gmail: $email"
        } else {
            welcomeTextView.text = "OOPS"
            emailTextView.text = "Email not available"
        }
        //home button logic
        val homeButton: Button = findViewById(R.id.homeButton)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Logout button logic
        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            // Remove only the login session, not the stored credentials
            sharedPreferences.edit {
                remove("isLoggedIn") // Logout user but keep credentials
                apply()
            }

            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

            // Redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        // About and feedback
        val aboutFeedbackButton: Button = findViewById(R.id.aboutFeedbackButton)
        aboutFeedbackButton.setOnClickListener {
            val intent = Intent(this, AboutFeedbackActivity::class.java)
            startActivity(intent)
        }

    }
}
