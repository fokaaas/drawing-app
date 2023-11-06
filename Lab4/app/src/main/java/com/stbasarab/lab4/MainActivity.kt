package com.stbasarab.lab4

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.stbasarab.lab4.editors.EllipseEditor
import com.stbasarab.lab4.editors.LineEditor
import com.stbasarab.lab4.editors.PointEditor
import com.stbasarab.lab4.editors.RectangleEditor

class MainActivity : AppCompatActivity() {
  private lateinit var shapeObjectEditor: ShapeObjectsEditor
  private var prevItem: MenuItem? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    shapeObjectEditor = ShapeObjectsEditor(this)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.objects_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.title) {
      getString(R.string.point_title) -> shapeObjectEditor.editor = PointEditor()
      getString(R.string.line_title) -> shapeObjectEditor.editor = LineEditor()
      getString(R.string.rectangle_title) -> shapeObjectEditor.editor = RectangleEditor()
      getString(R.string.ellipse_title) -> shapeObjectEditor.editor = EllipseEditor()
      getString(R.string.objects_title) -> return true
    }
    prevItem?.icon?.setTint(getColor(R.color.white))
    item.icon?.setTint(getColor(R.color.blue))
    prevItem = item
    title = item.titleCondensed
    setContentView(shapeObjectEditor)
    return super.onOptionsItemSelected(item);
  }
}