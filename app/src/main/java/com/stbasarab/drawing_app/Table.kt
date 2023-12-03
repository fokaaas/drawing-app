package com.stbasarab.drawing_app

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment

class Table: Fragment() {
  private lateinit var tableLayout: TableLayout
  private lateinit var scrollView: ScrollView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val table = inflater.inflate(R.layout.table, container, false)
    tableLayout = table.findViewById(R.id.table_layout)
    scrollView = table.findViewById(R.id.table_scroll)
    return table
  }

  private fun getTextView(value: String): TextView {
    val textView = TextView(tableLayout.context)
    textView.text = value
    textView.setPadding(3, 3, 3, 3)
    textView.gravity = Gravity.CENTER
    return textView
  }

  fun addRow(name: String, values: List<Float>) {
    val tableRow = TableRow(tableLayout.context)
    tableRow.addView(getTextView(name))
    for (value in values) {
      val view = getTextView(String.format("%.2f", value))
      tableRow.addView(view)
    }
    tableLayout.addView(tableRow)
    scrollView.post { scrollView.fullScroll(View.FOCUS_DOWN) }
  }

  fun removeLastRow() {
    val count = tableLayout.childCount
    tableLayout.removeViewAt(count - 1)
  }

  fun removeAllRows() {
    val count = tableLayout.childCount
    tableLayout.removeViews(1, count - 1)
  }
}