package com.stbasarab.drawing_app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment

class Table(private val mainActivityInterface: MainActivityInterface): Fragment() {
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
    val params = TableRow.LayoutParams(
      TableRow.LayoutParams.MATCH_PARENT,
      TableRow.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(3)
    textView.text = value
    textView.gravity = Gravity.CENTER
    textView.layoutParams = params
    textView.setBackgroundColor(Color.WHITE)
    return textView
  }

  private fun changeRowColor(tableRow: TableRow, color: Int) {
    for (i in 0 until tableRow.childCount) {
      val child = tableRow.getChildAt(i)
      child.setBackgroundColor(color)
    }
  }

  private fun onClickRowListener(view: View) {
    val tableRow = view as TableRow
    val current = (view.getChildAt(0).background as ColorDrawable).color
    val index = tableLayout.indexOfChild(view) - 1
    if (current == Color.WHITE) {
      changeRowColor(tableRow, requireContext().getColor(R.color.purple))
      mainActivityInterface.onHighlightElement(index, true)
    } else {
      changeRowColor(tableRow, Color.WHITE)
      mainActivityInterface.onHighlightElement(index, false)
    }
  }

  private fun onLongClickRowListener(view: View): Boolean {
    val index = tableLayout.indexOfChild(view) - 1
    tableLayout.removeView(view)
    mainActivityInterface.onRemoveShapeFromRow(index)
    return true
  }

  fun addRow(name: String, values: List<Float>) {
    val tableRow = TableRow(tableLayout.context)
    tableRow.addView(getTextView(name))

    for (value in values) {
      val view = getTextView(String.format("%.2f", value))
      tableRow.addView(view)
    }

    tableRow.setBackgroundColor(requireContext().getColor(R.color.violet))
    tableRow.setOnClickListener { onClickRowListener(it) }
    tableRow.setOnLongClickListener { onLongClickRowListener(it) }
    tableLayout.addView(tableRow)
    scrollView.post { scrollView.fullScroll(View.FOCUS_DOWN) }
  }

  fun removeAllRows() {
    val count = tableLayout.childCount - 1
    tableLayout.removeViews(1, count)
  }
}