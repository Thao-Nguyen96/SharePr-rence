package com.nxt.sharedpreferencesobject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private var exampleAdapter: ExampleAdapter? = null
    private var listExampleItem: ArrayList<ExampleItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listExampleItem = ArrayList()
        //truyen du lieu vao list data
        loadData()
        buildRecyclerView()
        setInsertButton()

        button_save.setOnClickListener {
            saveData()
        }

    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task", null)

        val type: Type = object : TypeToken<ArrayList<ExampleItem>>() {}.type
        listExampleItem = gson.fromJson(json, type)

        if (listExampleItem == null) {
            listExampleItem = ArrayList()
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val gson = Gson()

        val json = gson.toJson(listExampleItem)
        editor.putString("task", json)
        editor.apply()

        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
    }

    private fun buildRecyclerView() {
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)
        exampleAdapter = ExampleAdapter(listExampleItem!!)

        recyclerview.adapter = exampleAdapter
    }

    private fun setInsertButton() {
        button_insert.setOnClickListener {
            val line1 = edittext_line_1.text.toString()
            val line2 = edittext_line_2.text.toString()

            listExampleItem?.add(ExampleItem(line1, line2))
            exampleAdapter?.notifyItemInserted(listExampleItem!!.size)

            edittext_line_1.text.clear()
            edittext_line_2.text.clear()
        }
    }
}