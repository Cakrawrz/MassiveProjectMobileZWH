package com.infinite.massiveprojectmobilezwh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MasukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)
        val bt_pengguna = findViewById<Button>(R.id.Button1)
        bt_pengguna.setOnClickListener {
            Intent(this, BerandaListActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}