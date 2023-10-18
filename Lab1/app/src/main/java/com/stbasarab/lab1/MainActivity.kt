package com.stbasarab.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), DialogListenerInterface {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.work1).setOnClickListener {
      Work1()
        .setListener(this)
        .show(supportFragmentManager, "Work1")
    }

    findViewById<Button>(R.id.work2).setOnClickListener {
      Work2()
        .setListener(this)
        .show(supportFragmentManager, "Work2")
    }
  }

  override fun dialogListener(dialog: Int, button: Int, option: String?) {
    if (dialog == R.string.work1 && button == R.string.next) {
      Work1_1()
        .setListener(this)
        .show(supportFragmentManager, "Work1_1")
    }

    if (dialog == R.string.work2 && button == R.string.yes) {
      findViewById<TextView>(R.id.grouptxt).text = option
    }

    if (dialog == R.string.work1_1 && button == R.string.back) {
      Work1()
        .setListener(this)
        .show(supportFragmentManager, "Work1")
    }
  }
}