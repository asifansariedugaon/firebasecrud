package com.example.firebasecrud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(), StudentListAdapter.SetOnStudentClickListener {

    private var db = FirebaseFirestore.getInstance()
    private lateinit var listView: ListView
    private lateinit var studentAdapter: StudentListAdapter
    lateinit var floatButton: FloatingActionButton

    @SuppressLint("MissingInflatedId", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        floatButton = findViewById(R.id.addItem_FloatButton)

        floatButton.setOnClickListener {
            startActivity(Intent(this,AddDetailActivity::class.java))
            finish()
        }

        val userArray = ArrayList<StudentModel>()
        studentAdapter = StudentListAdapter(this, userArray, this)

        getStudentList()

    }

    private fun getStudentList() {

        db.collection("student").get()
            .addOnSuccessListener {
                val data = it.toObjects(StudentModel::class.java)
                studentAdapter = StudentListAdapter(this, data, this)
                listView.adapter = studentAdapter
                Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onUpdateClick(student: StudentModel) {
       val intent = Intent(this,UpdateDetailActivity::class.java)
        intent.putExtra("key_id", student.id.toString())
        intent.putExtra("key_name", student.name.toString())
        intent.putExtra("key_email", student.email.toString())
        intent.putExtra("key_number", student.number.toString())
        startActivity(intent)
        finish()
    }

    override fun onDeleteClick(student: StudentModel) {
      deleteStudent(student.id.toString())
    }

    private fun deleteStudent(id:String){
        db.collection("student").document(id).delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show()
                deleteStudentList()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Deleted Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteStudentList(){
        db.collection("student").get()
            .addOnSuccessListener {
                val data = it.toObjects(StudentModel::class.java)
                studentAdapter = StudentListAdapter(this, data, this)
                listView.adapter = studentAdapter
                Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "File deleted failed", Toast.LENGTH_SHORT).show()
            }
    }
}


