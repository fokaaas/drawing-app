package com.stbasarab.drawing_app

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stbasarab.drawing_app.shapes.CircleLineShape
import com.stbasarab.drawing_app.shapes.CubeShape
import com.stbasarab.drawing_app.shapes.EllipseShape
import com.stbasarab.drawing_app.shapes.LineShape
import com.stbasarab.drawing_app.shapes.PointShape
import com.stbasarab.drawing_app.shapes.RectangleShape

class MainActivity : AppCompatActivity(), MainActivityInterface {
  private lateinit var myEditor: MyEditor
  private var prevButton: ImageButton? = null
  private lateinit var table: Table
  private lateinit var tableContainer: LinearLayout
  private lateinit var fileManager: FileManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    tableContainer = findViewById(R.id.table_container)
    fileManager = FileManager(this)
    myEditor = MyEditor.getInstance(this)
    myEditor.setShape(PointShape())
    title = getString(R.string.point_title)

    table = Table(this)
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

  fun onRemoveButton(item: MenuItem) {
    myEditor.removeAll()
    table.removeAllRows()
  }

  override fun onHighlightElement(index: Int, isHighlight: Boolean) {
    if (isHighlight) {
      myEditor.highlightShape(index)
    } else {
      myEditor.setDefaultStyle(index)
    }
  }

  override fun onRemoveShapeFromRow(index: Int) {
    myEditor.removeShapeByIndex(index)
  }

  fun onTableButton(view: View) {
    val imageButton = findViewById<ImageButton>(view.id)
    if (tableContainer.visibility == View.GONE) {
      tableContainer.visibility = View.VISIBLE
      imageButton.drawable.setTint(getColor(R.color.purple))
    } else {
      tableContainer.visibility = View.GONE
      imageButton.drawable.setTint(getColor(R.color.white))
    }
  }

  fun onInfoOption(item: MenuItem) {
    val alertDialogBuilder = AlertDialog.Builder(this)
    alertDialogBuilder.setTitle(item.title)
    alertDialogBuilder.setMessage(getString(R.string.info))
    alertDialogBuilder.setPositiveButton(getString(R.string.close)) { dialog, _ -> dialog.dismiss() }

    val alertDialog = alertDialogBuilder.create()
    alertDialog.show()
  }

  fun onOpenOption(item: MenuItem) {
    val names = fileManager.getNamesOfFiles()
    var index: Int = -1
    AlertDialog.Builder(this)
      .setTitle(item.title)
      .setSingleChoiceItems(names, index) { _, which ->
        index = which
      }
      .setPositiveButton(R.string.open_title) { _, _ ->
        if (index != -1) {
          val data = fileManager.readFile(names!![index])
          table.removeAllRows()
          myEditor.loadData(data)
        }
      }
      .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
        dialog.cancel()
      }
      .create()
      .show()
  }

  fun onSaveOption(item: MenuItem) {
    val input = EditText(this)
    AlertDialog.Builder(this)
      .setTitle(R.string.name_of_file_title)
      .setView(input)
      .setPositiveButton(R.string.save_title) { _, _ ->
        val fileName = input.text.toString()
        val alert = getString(R.string.success)
        fileManager.writeFile(fileName, getFileData())
        Toast.makeText(this, alert, Toast.LENGTH_SHORT).show()
      }
      .setNegativeButton(R.string.cancel) { dialog, _ ->
        dialog.cancel()
      }
      .create()
      .show()
  }

  private fun getFileData(): String {
    val shapes = myEditor.getShapes()
    var data = ""
    for (shape in shapes) {
      val coords = shape.getCoordinates()
      data += "${shape.javaClass.name}\t"
      coords.forEach { data += "$it\t" }
      data = data.dropLast(1)
      data += "\n"
    }
    return data.dropLast(1)
  }

  private fun selectAction(name: String) {
    when (name) {
      getString(R.string.point_title) -> myEditor.setShape(PointShape())
      getString(R.string.line_title) -> myEditor.setShape(LineShape())
      getString(R.string.rectangle_title) -> myEditor.setShape(RectangleShape())
      getString(R.string.ellipse_title) -> myEditor.setShape(EllipseShape())
      getString(R.string.circle_line_title) -> myEditor.setShape(CircleLineShape())
      getString(R.string.cube_title) -> myEditor.setShape(CubeShape())
    }
  }

  override fun getEditorViewInstance(): MyEditor {
    return findViewById(R.id.editor)
  }
}