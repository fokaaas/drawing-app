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

class MainActivity : AppCompatActivity() {
  private lateinit var myEditor: MyEditor
  private lateinit var prevButton: ImageButton

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val point = findViewById<ImageButton>(R.id.point_tool)
    myEditor = findViewById(R.id.editor)
    prevButton = point
    title = point.tag.toString()
    myEditor.shape = PointShape(Color.BLACK, Color.TRANSPARENT)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.objects_menu, menu)
    return true
  }

  fun onViewSelected(view: View) {
    val imageButton = findViewById<ImageButton>(view.id)
    val tag = view.tag.toString()
    imageButton.drawable.setTint(getColor(R.color.purple))
    updateState(tag)
    prevButton = imageButton
    selectAction(tag)
  }

  private fun updateState(name: String) {
    prevButton.drawable?.setTint(getColor(R.color.white))
    title = name
  }

  fun onItemSelected(item: MenuItem) {
    val resourceName = resources.getResourceEntryName(item.itemId)
    val title = item.title.toString()
    if (!resourceName.endsWith("tool")) updateState(title)
    selectAction(title)
  }

  private fun selectAction(name: String) {
    when (name) {
      getString(R.string.point_title) -> myEditor.shape = PointShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.line_title) -> myEditor.shape = LineShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.rectangle_title) -> myEditor.shape = RectangleShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.ellipse_title) -> myEditor.shape = EllipseShape(Color.BLACK, Color.CYAN)
      getString(R.string.circle_line_title) -> myEditor.shape = CircleLineShape(Color.BLACK, Color.CYAN)
      getString(R.string.cube_title) -> myEditor.shape = CubeShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.back_title) -> myEditor.removeLastShape()
      getString(R.string.next_title) -> myEditor.restoreShape()
      getString(R.string.delete_title) -> myEditor.removeAll()
    }
  }
}