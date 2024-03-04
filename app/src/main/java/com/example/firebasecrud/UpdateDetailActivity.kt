package com.example.firebasecrud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class UpdateDetailActivity : AppCompatActivity() {

     private var db = FirebaseFirestore.getInstance()
    private lateinit var updateName:EditText
    private lateinit var updateEmail:EditText
    private lateinit var updateNumber:EditText
    private lateinit var updateBtn:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_detail)

        updateName = findViewById(R.id.updateName_appCompatEditText)
        updateEmail = findViewById(R.id.updateEmail_appCompatEditText)
        updateNumber = findViewById(R.id.updateNumber_appCompatEditText)
        updateBtn = findViewById(R.id.updateDetail_button)

        val id = intent.extras?.getString("key_id")
        val name = intent.extras?.getString("key_name")
        val email = intent.extras?.getString("key_email")
        val number = intent.extras?.getString("key_number")

        updateName.setText(name)
        updateEmail.setText(email)
        updateNumber.setText(number)

        updateBtn.setOnClickListener {
            val sName = updateName.text.toString()
            val sEmail = updateEmail.text.toString()
            val sNumber = updateNumber.text.toString()


            val map = mapOf("name" to sName, "email" to sEmail, "number" to sNumber)

            db.collection("student").document("$id").update(map)
                .addOnSuccessListener {
                    Toast.makeText(this, "User update", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = F
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }

        }
    }
}