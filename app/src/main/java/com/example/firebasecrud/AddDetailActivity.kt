package com.example.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class AddDetailActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var addName: androidx.appcompat.widget.AppCompatEditText
    private lateinit var addEmail: androidx.appcompat.widget.AppCompatEditText
    private lateinit var addNumber: androidx.appcompat.widget.AppCompatEditText
    private lateinit var addDetailBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_detail)

        addName = findViewById(R.id.adaName_appCompatEditText)
        addEmail = findViewById(R.id.addEmail_appCompatEditText)
        addNumber = findViewById(R.id.addNumber_appCompatEditText)
        addDetailBtn = findViewById(R.id.addDetail_button)

        addDetailBtn.setOnClickListener {
            studentDetailAdd()
        }

    }
    private fun studentDetailAdd() {
        val userName = addName.text.toString()
        val userEmail = addEmail.text.toString()
        val userNumber = addNumber.text.toString()
        val uid = UUID.randomUUID().toString()

        val hashMap = hashMapOf(
            "id" to uid,
            "name" to userName,
            "email" to userEmail,
            "number" to userNumber
        )
        db.collection("student").document(uid).set(hashMap)
            .addOnSuccessListener {
            Toast.makeText(this, "Insert Data", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
                finish()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show()

            }
    }
}