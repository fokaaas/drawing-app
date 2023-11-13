package com.stbasarab.lab4

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.stbasarab.lab4.shapes.CircleLineShape
import com.stbasarab.lab4.shapes.CubeShape
import com.stbasarab.lab4.shapes.EllipseShape
import com.stbasarab.lab4.shapes.LineShape
import com.stbasarab.lab4.shapes.PointShape
import com.stbasarab.lab4.shapes.RectangleShape

class MainActivity : AppCompatActivity() {
  private lateinit var myEditor: MyEditor
  private var prevItem: MenuItem? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    myEditor = MyEditor(this)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.objects_menu, menu)
    return true
  }

  fun activateButton(item: MenuItem) {
    prevItem?.icon?.setTint(getColor(R.color.white))
    item.icon?.setTint(getColor(R.color.blue))
    prevItem = item
    title = item.titleCondensed
    onOptionsItemSelected(item)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.title) {
      getString(R.string.point_title) -> myEditor.shape = PointShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.line_title) -> myEditor.shape = LineShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.rectangle_title) -> myEditor.shape = RectangleShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.ellipse_title) -> myEditor.shape = EllipseShape(Color.BLACK, Color.CYAN)
      getString(R.string.circle_line_title) -> myEditor.shape = CircleLineShape(Color.BLACK, Color.CYAN)
      getString(R.string.cube_title) -> myEditor.shape = CubeShape(Color.BLACK, Color.TRANSPARENT)
      getString(R.string.back_title) -> myEditor.removeLastShape()
      getString(R.string.delete_title) -> myEditor.removeAll()
      getString(R.string.objects_title) -> return true
    }
    setContentView(myEditor)
    return super.onOptionsItemSelected(item)
  }
}