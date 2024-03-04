package com.example.firebasecrud

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class StudentListAdapter(
    private val context: Context,
    private val studentList: List<StudentModel>,
    private val onStudentClickListener: SetOnStudentClickListener
) : BaseAdapter() {
    override fun getCount(): Int {
        return studentList.size
    }

    override fun getItem(position: Int): Any {
        return studentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layout = LayoutInflater.from(context).inflate(R.layout.student_list_ui, parent, false)
        val sName: TextView = layout.findViewById(R.id.studentName_textView)
        val sEmail = layout.findViewById<TextView>(R.id.studentEmail_textView)
        val sNumber = layout.findViewById<TextView>(R.id.studentNumber_textView)
        val sUpdate: Button = layout.findViewById(R.id.updateBtn_button)
        val sDelete: Button = layout.findViewById(R.id.deleteBtn_button)

        sName.text = studentList[position].name
        sEmail.text = studentList[position].email
        sNumber.text = studentList[position].number

        sUpdate.setOnClickListener {
            onStudentClickListener.onUpdateClick(studentList[position])
        }

        sDelete.setOnClickListener {
            onStudentClickListener.onDeleteClick(studentList[position])
        }

        return layout
    }

    interface SetOnStudentClickListener {
        fun onUpdateClick(student: StudentModel)
        fun onDeleteClick(student: StudentModel)

    }


}