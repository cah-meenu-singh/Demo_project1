package com.example.android_first

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserDataActivity : AppCompatActivity() {
    private lateinit var textField1: EditText
    private lateinit var textField2: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private lateinit var cancelButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    private val PREFS_NAME = "MyPrefs"  // Name for SharedPreferences file
    private val KEY_TEXT_FIELD1 = "textField1"
    private val KEY_TEXT_FIELD2 = "textField2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)  // Make sure to replace this with your layout resource

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Bind the views
        textField1 = findViewById(R.id.textField1)
        textField2 = findViewById(R.id.textField2)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)
        cancelButton = findViewById(R.id.cancelButton)

        // Load previously saved values into the EditTexts (if any)
        loadSavedData()

        // Save Button Click Listener
        saveButton.setOnClickListener {
            saveData()
        }

        // Delete Button Click Listener
        deleteButton.setOnClickListener {
            deleteData()
        }

        // Cancel Button Click Listener (optional, to clear fields)
        cancelButton.setOnClickListener {
            clearFields()
        }

        // Back Button (handle using navigation)
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    // Save data to SharedPreferences
    private fun saveData() {
        val text1 = textField1.text.toString()
        val text2 = textField2.text.toString()

        val editor = sharedPreferences.edit()
        editor.putString(KEY_TEXT_FIELD1, text1)
        editor.putString(KEY_TEXT_FIELD2, text2)
        editor.apply()  // Apply changes asynchronously

        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show() // Optional: Show confirmation message
    }

    // Load data from SharedPreferences into the EditTexts
    private fun loadSavedData() {
        val text1 = sharedPreferences.getString(KEY_TEXT_FIELD1, "")
        val text2 = sharedPreferences.getString(KEY_TEXT_FIELD2, "")

        textField1.setText(text1)
        textField2.setText(text2)
    }

    // Delete data from SharedPreferences
    private fun deleteData() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_TEXT_FIELD1)
        editor.remove(KEY_TEXT_FIELD2)
        editor.apply()  // Apply changes asynchronously

        clearFields()

        Toast.makeText(this, "Data Deleted!", Toast.LENGTH_SHORT).show() // Optional: Show confirmation message
    }

    // Clear the fields in the UI (reset EditText fields)
    private fun clearFields() {
        textField1.setText("")
        textField2.setText("")
    }
}
