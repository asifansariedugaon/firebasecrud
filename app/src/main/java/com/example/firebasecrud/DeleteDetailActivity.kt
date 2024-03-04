package com.example.firebasecrud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class DeleteDetailActivity : AppCompatActivity() {

    var db= FirebaseFirestore.getInstance()
    lateinit var deleteName:EditText
    lateinit var deleteEmail:EditText
    lateinit var deleteNumber:EditText
    lateinit var deleteBtn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_detail)

        deleteName = findViewById(R.id.deleteName_appCompatEditText)
        deleteEmail = findViewById(R.id.deleteEmail_appCompatEditText)
        deleteNumber = findViewById(R.id.deleteNumber_appCompatEditText)
        deleteBtn = findViewById(R.id.deleteBtn_button)

    }
}