package com.example.notes2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class addActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ftadd = Firebase.firestore
        ftadd.collection("User").document("test").set(mapOf("name"  to   "My book"))

        val edTex: EditText = findViewById(R.id.editextmain)
        val edMintx: EditText = findViewById(R.id.editextdesc)
        val buuton: Button = findViewById(R.id.save)
        val lasbtn: TextView = findViewById(R.id.back)


        lasbtn.setOnClickListener {
            val Lasintent = Intent(this, MainActivity::class.java)
            startActivity(Lasintent)
        }


        buuton.setOnClickListener {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = dateFormat.format(Date())

        val edtex = edTex.text.toString().trim()
        val edMitx = edMintx.text.toString().trim()

        if(edMitx == ""|| edtex == "" ){
            Toast.makeText(this,"Не все поля заполнены", Toast.LENGTH_LONG).show()}

        else {
            ftadd.collection("notes").document().set(nates(edtex,edMitx, date))

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)}
        }



    }


}
