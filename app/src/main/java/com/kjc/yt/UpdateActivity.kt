package com.kjc.yt
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kjc.yt.databinding.ActivityUpdateBinding


class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)

        Log.d("UpdateNoteActivity", "Received noteId: $noteId") // Debugging log

        val note = db.getNoteByID(noteId)
        if (note == null) {
            Toast.makeText(this, "Note not found!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()

            if (newTitle.isBlank() || newContent.isBlank()) {
                Toast.makeText(this, "Title and Content cannot be empty!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val updatedNote = Note(noteId, newTitle, newContent)
            db.updateNote(updatedNote)

            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

