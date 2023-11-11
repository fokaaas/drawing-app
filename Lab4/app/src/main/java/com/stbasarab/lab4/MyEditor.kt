package com.stbasarab.lab4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.stbasarab.lab4.shapes.Shape
import kotlin.reflect.full.primaryConstructor

private const val SHAPES_LENGTH = 103

open class MyEditor(context: Context): View(context) {
  lateinit var shape: Shape

  private var shapes: Array<Shape?> = Array(SHAPES_LENGTH) { null }
  private var isLayout = true
  private lateinit var bitmap: Bitmap

  private lateinit var canvas: Canvas
  
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
        addShape(shape)
        newShape()
      }
    }

    invalidate()
    return true
  }

  private fun addShape(shape: Shape) {
    val index = shapes.indexOfFirst { it == null }
    if (index != -1) {
      shapes[index] = shape
    } else {
      val alert = context.getString(R.string.alert)
      Toast.makeText(context, alert, Toast.LENGTH_SHORT).show()
    }
  }

  private fun newShape() {
    val constructor = shape::class.primaryConstructor
    shape = constructor!!.call(shape.borderColor, shape.fillColor)
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