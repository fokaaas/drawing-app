package com.stbasarab.drawing_app

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.stbasarab.drawing_app.shapes.CircleLineShape
import com.stbasarab.drawing_app.shapes.CubeShape
import com.stbasarab.drawing_app.shapes.EllipseShape
import com.stbasarab.drawing_app.shapes.LineShape
import com.stbasarab.drawing_app.shapes.PointShape
import com.stbasarab.drawing_app.shapes.RectangleShape

class MainActivity : AppCompatActivity(), GetEditorViewInterface {
  private lateinit var myEditor: MyEditor
  private var prevButton: ImageButton? = null
  private lateinit var table: Table

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    myEditor = MyEditor.getInstance(this)
    myEditor.setShape(PointShape(Color.BLACK, Color.TRANSPARENT))
    title = getString(R.string.point_title)

    table = Table()
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.table_container, table)
    fragmentTransaction.commit()
    myEditor.setShapeTable(table)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.objects_menu, menu)
    return true
  }

  fun onViewSelected(view: View) {
    if (prevButton == view) return
    val imageButton = findViewById<ImageButton>(view.id)
    val tag = imageButton.tag.toString()
    imageButton.drawable.setTint(getColor(R.color.purple))
    updateState(tag)
    prevButton = imageButton
    selectAction(tag)
  }

  private fun updateState(name: String) {
    prevButton?.drawable?.setTint(getColor(R.color.white))
    title = name
  }

  fun onItemSelected(item: MenuItem) {
    val title = item.title.toString()
    updateState(title)
    selectAction(title)
  }

  fun onBackButton(item: MenuItem) {
    val isRemoved = myEditor.removeLastShape()
    if (isRemoved) table.removeLastRow()
  }

  fun onRemoveButton(item: MenuItem) {
    myEditor.removeAll()
    table.removeAllRows()
  }

  fun onRestoreButton(item: MenuItem) {
    val shape = myEditor.restoreShape()
    if (shape != null) {
      table.addRow(shape.name, shape.getCoordinates())
    }
  }

  private fun selectAction(name: String) {
    when (name) {
      getString(R.string.point_title) -> myEditor.setShape(PointShape(Color.BLACK, Color.TRANSPARENT))
      getString(R.string.line_title) -> myEditor.setShape(LineShape(Color.BLACK, Color.TRANSPARENT))
      getString(R.string.rectangle_title) -> myEditor.setShape(RectangleShape(Color.BLACK, Color.TRANSPARENT))
      getString(R.string.ellipse_title) -> myEditor.setShape(EllipseShape(Color.BLACK, Color.CYAN))
      getString(R.string.circle_line_title) -> myEditor.setShape(CircleLineShape(Color.BLACK, Color.CYAN))
      getString(R.string.cube_title) -> myEditor.setShape(CubeShape(Color.BLACK, Color.TRANSPARENT))
    }
  }

  override fun getEditorViewInstance(): MyEditor {
    return findViewById(R.id.editor)
  }
}