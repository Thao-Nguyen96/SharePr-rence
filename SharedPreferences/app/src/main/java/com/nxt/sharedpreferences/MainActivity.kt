package com.nxt.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val SHARE_PREFS = "sharePres"
        const val TEXT = "text"
        const val SWITCH1 = "switch1"
    }

    private var text: String? = null
    private var switchOnOff: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apply_text_button.setOnClickListener {
            textview.text = edittext.text.toString()
        }

        save_button.setOnClickListener {
            saveData()
        }
        loadData()
        updateView()
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences(SHARE_PREFS, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(TEXT, textview.text.toString())
        editor.putBoolean(SWITCH1, switch1.isChecked)

        editor.apply()
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences(SHARE_PREFS, MODE_PRIVATE)

        text = sharedPreferences.getString(TEXT, "")

        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false)
    }

    private fun updateView() {
        textview.text = text
        switch1.isChecked = switchOnOff
    }
}