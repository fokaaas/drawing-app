package com.stbasarab.drawing_app

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.stbasarab.drawing_app.shapes.Shape
import kotlin.reflect.full.primaryConstructor

private const val SHAPES_LENGTH = 103

class MyEditor(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
  companion object {
    private var instance: MyEditor? = null

    fun getInstance(editor: GetInstanceInterface?): MyEditor {
      if (instance == null) {
        instance = editor?.getEditorInstance()
      }
      return instance!!
    }
  }

  lateinit var shape: Shape

  private var shapes: Array<Shape?> = Array(SHAPES_LENGTH) { null }
  private var removedShapes: Array<Shape?> = Array(SHAPES_LENGTH) { null }
  private var isLayout = true
  private lateinit var bitmap: Bitmap
  private lateinit var canvas: Canvas

  private fun getEditorViewInstance(): MyEditor {
    return findViewById(R.id.editor)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    shape.onTouchUp(event!!.x, event.y)

    when (event.action) {
      MotionEvent.ACTION_DOWN -> {
        isLayout = true
        shape.onTouchDown(event.x, event.y)
      }
      MotionEvent.ACTION_MOVE -> {
        invalidate()
      }
      MotionEvent.ACTION_UP -> {
        isLayout = false
        addShape(shape, shapes)
        createShape()
      }
    }

    resetRestore()
    return true
  }
  private fun addShape(shape: Shape?, shapes: Array<Shape?>) {
    val index = shapes.indexOfFirst { it == null }
    if (index != -1) {
      shapes[index] = shape
    } else {
      val alert = context.getString(R.string.alert)
      Toast.makeText(context, alert, Toast.LENGTH_SHORT).show()
    }
  }

  private fun createShape() {
    val constructor = shape::class.primaryConstructor
    shape = constructor!!.call(shape.borderColor, shape.fillColor)
  }

  fun removeLastShape() {
    val i = shapes.indexOfLast { it != null }
    if (i != -1) {
      addShape(shapes[i], removedShapes)
      shapes[i] = null
    }
    invalidate()
  }

  fun restoreShape() {
    val i = removedShapes.indexOfLast { it != null }
    if (i != -1) {
      addShape(removedShapes[i], shapes)
      removedShapes[i] = null
    }
    invalidate()
  }

  fun removeAll() {
    for (i in shapes.indices) {
      shapes[i] = null
    }
    resetRestore()
  }

  private fun resetRestore() {
    for (i in removedShapes.indices) {
      removedShapes[i] = null
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
    if (isLayout) shape.drawFrame(canvas)
  }
}