package com.kjc.yt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AboutFeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_feedback)

        val feedbackRatingBar: RatingBar = findViewById(R.id.feedbackRatingBar)
        val submitButton: Button = findViewById(R.id.submitButton)
        val returnHomeButton: Button = findViewById(R.id.returnHomeButton)

        // Submit Button Logic
        submitButton.setOnClickListener {
            val rating = feedbackRatingBar.rating
            Toast.makeText(this, "Thank you for your feedback: $rating stars!", Toast.LENGTH_SHORT).show()
        }

        // Return to Home Button Logic
        returnHomeButton.setOnClickListener {
            val intent = Intent(this, NavbarActivity::class.java)
            startActivity(intent)
            finish()
        }
        
    }
}
