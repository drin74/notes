package com.example.notes2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateBD()

        val btn: TextView = findViewById(R.id.addbut)
        btn.setOnClickListener{
            val intent = Intent(this, addActivity2::class.java)
            startActivity(intent)


        }
    }

    override fun onResume() {
        super.onResume()
        updateBD()
    }


    fun updateBD() {

        val recy: RecyclerView = findViewById(R.id.recycleview)
        val fsnot = Firebase.firestore
        val items = mutableListOf<nates>()

        val reportAdapter = notesAdapter(items, context = this)
        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = reportAdapter

        fsnot.collection("notes")
            .get()
            .addOnSuccessListener { documents ->
                items.clear()
                for (document in documents) {
                    val report = nates(
                        document.getString("text") ?: "",
                        document.getString("mainText") ?: "",
                        document.getString("data") ?: "",
                        documentId = document.id
                    )

                    items.add(report)
                }
                reportAdapter.notifyDataSetChanged()
            }

    }





}