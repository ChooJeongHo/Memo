package com.example.memo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    var memo : String = ""
    var cateName : String = ""
    lateinit var cateTv : TextView
    lateinit var detailEt : EditText
    lateinit var saveBt : Button
    lateinit var deleteBt : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        cateTv = findViewById(R.id.cateTv)
        detailEt = findViewById(R.id.detailEt)
        saveBt = findViewById(R.id.saveBt)
        deleteBt = findViewById(R.id.deleteBt)

        cateName = intent.getStringExtra("cateName")!!

        cateTv.setText(cateName)

        var pref : SharedPreferences = getSharedPreferences("wordPref", MODE_PRIVATE)
        memo = pref.getString(cateName,"nothing")!!
        detailEt.setText(memo)

        saveBt.setOnClickListener {
            memo = detailEt.text.toString()
            var pref : SharedPreferences = getSharedPreferences("wordPref", MODE_PRIVATE)
            var edit : SharedPreferences.Editor = pref.edit()
            edit.putString(cateName,memo)
            edit.commit()
        }
        deleteBt.setOnClickListener {
            var pref : SharedPreferences = getSharedPreferences("wordPref", MODE_PRIVATE)
            var edit : SharedPreferences.Editor = pref.edit()
            edit.remove(cateName)
            edit.commit()
        }
    }
}