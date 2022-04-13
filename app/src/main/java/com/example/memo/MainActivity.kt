package com.example.memo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val cateArr: ArrayList<String> = ArrayList()

    lateinit var inputEt: EditText
    lateinit var inputBt: Button
    lateinit var rv: RecyclerView
    lateinit var myRvAdapter : MyRvAdapter
    lateinit var calendarView : CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputEt = findViewById(R.id.inputEt)
        inputBt = findViewById(R.id.inputBt)
        rv = findViewById(R.id.rv)
        calendarView = findViewById(R.id.calendarView)

        rv.setBackgroundResource(R.drawable.ic_launcher_background)

        var cal  = GregorianCalendar(2020,2,15)
        calendarView.date = cal.timeInMillis

        myRvAdapter = MyRvAdapter(this, cateArr)
        rv.adapter = myRvAdapter
        rv.layoutManager = LinearLayoutManager(this)

        inputBt.setOnClickListener {
            var str = inputEt.text.toString()
            inputEt.setText("")
            cateArr.add(str)
            myRvAdapter.notifyDataSetChanged()
        }
    }

    inner class MyRvAdapter(val context: Context, val arr: ArrayList<String>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.tv.setText(arr.get(position))
            holder.tv.setOnClickListener {
                var intent : Intent =  Intent(this@MainActivity, com.example.memo.DetailActivity::class.java)
                intent.putExtra("cateName", cateArr.get(position))
                startActivity(intent)
            }

        }


        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tv: TextView = itemView!!.findViewById(R.id.tv)

        }
    }
}