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
    textView.text = value
    textView.setPadding(3, 3, 3, 3)
    textView.gravity = Gravity.CENTER
    return textView
  }

  private fun onClickRowListener(view: View) {
    val current = (view.background as ColorDrawable).color
    val index = tableLayout.indexOfChild(view) - 1
    if (current == 0) {
      view.setBackgroundColor(requireContext().getColor(R.color.purple))
      mainActivityInterface.onHighlightElement(index, true)
    } else {
      view.setBackgroundColor(Color.TRANSPARENT)
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

    tableRow.setBackgroundColor(0)
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