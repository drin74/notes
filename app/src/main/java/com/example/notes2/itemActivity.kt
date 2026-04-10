package com.example.notes2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class itemActivity : AppCompatActivity() {

    private lateinit var documentId: String
    private lateinit var maintee: EditText
    private lateinit var title: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        title = findViewById(R.id.textmain)
        maintee = findViewById(R.id.textdesc)
        val nexn: TextView = findViewById(R.id.back)


        documentId = intent.getStringExtra("DOC_ID") ?: ""
        val itemTitle = intent.getStringExtra("itemTitle") ?: ""
        val itemMainTitle = intent.getStringExtra("itemMainTitle") ?: ""


        title.setText(itemTitle)
        maintee.setText(itemMainTitle)


        nexn.setOnClickListener {
            saveChanges()

            onBackPressedDispatcher.addCallback(this) {
                saveChanges()
            }
        }
    }

    private fun saveChanges() {
        val updatedTitle = title.text.toString()
        val updatedText = maintee.text.toString()

        val fsnot = Firebase.firestore


        fsnot.collection("notes").document(documentId)
            .update(
                mapOf(
                    "text" to updatedTitle,
                    "mainText" to updatedText
                )
            )

            .addOnSuccessListener {

                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }



}


