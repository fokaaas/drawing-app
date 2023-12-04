package com.stbasarab.drawing_app

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.stbasarab.drawing_app.shapes.Shape
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.primaryConstructor

private const val SHAPES_LENGTH = 103

class MyEditor(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
  companion object {
    private var instance: MyEditor? = null

    fun getInstance(editor: MainActivityInterface?): MyEditor {
      if (instance == null) {
        instance = editor?.getEditorViewInstance()
      }
      return instance!!
    }
  }

  private lateinit var currentShape: Shape
  private lateinit var table: Table

  private val shapes: Array<Shape?> = Array(SHAPES_LENGTH) { null }
  private var isLayout = true
  private lateinit var bitmap: Bitmap
  private lateinit var canvas: Canvas

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    currentShape.onTouchUp(event!!.x, event.y)

    when (event.action) {
      MotionEvent.ACTION_DOWN -> {
        isLayout = true
        currentShape.onTouchDown(event.x, event.y)
      }
      MotionEvent.ACTION_MOVE -> {
        invalidate()
      }
      MotionEvent.ACTION_UP -> {
        isLayout = false
        addShape(currentShape)
        createShape()
      }
    }

    invalidate()
    return true
  }

  private fun addShape(shape: Shape?) {
    val index = shapes.indexOfFirst { it == null }
    if (index != -1) {
      shapes[index] = shape
      table.addRow(shape!!.name, shape.getCoordinates())
    } else {
      val alert = context.getString(R.string.alert)
      Toast.makeText(context, alert, Toast.LENGTH_SHORT).show()
    }
  }

  private fun createShape() {
    val constructor = currentShape::class.primaryConstructor
    currentShape = constructor!!.call()
  }

  fun removeShapeByIndex(index: Int) {
    shapes[index] = null
    val nonNullShapes = shapes.filterNotNull().toTypedArray()
    shapes.fill(null)
    nonNullShapes.copyInto(shapes, startIndex = 0)
    invalidate()
  }

  fun removeAll() {
    shapes.fill(null)
    invalidate()
  }

  fun highlightShape(index: Int) {
    val shape = shapes[index]
    shape!!.setCustomBorder(Color.MAGENTA)
    invalidate()
  }

  fun setDefaultStyle(index: Int) {
    val shape = shapes[index]
    shape!!.setDefaultBorder()
    invalidate()
  }

  fun setShape(shape: Shape) {
    currentShape = shape
  }

  fun setShapeTable(table: Table) {
    this.table = table
  }

  fun getShapes(): Array<Shape> {
    return shapes.filterNotNull().toTypedArray()
  }

  fun loadData(data: String) {
    removeAll()
    val shapes = data.split("\n")
    for (shape in shapes) {
      val values = shape.split("\t")
      val shapeClass = Class.forName(values[0]).kotlin
      val instance = shapeClass.createInstance() as Shape
      instance.onTouchDown(values[1].toFloat(), values[2].toFloat())
      instance.onTouchUp(values[3].toFloat(), values[4].toFloat())
      addShape(instance)
    }
    invalidate()
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    canvas = Canvas(bitmap)
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    canvas.drawBitmap(bitmap, 0f, 0f, null)
    for (shape in shapes) shape?.draw(canvas)
    if (isLayout) currentShape.drawFrame(canvas)
  }
}