package com.stbasarab.lab4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import com.stbasarab.lab4.editors.Editor
import com.stbasarab.lab4.shapes.Shape

private const val SHAPES_LENGTH = 103

open class ShapeObjectsEditor(context: Context): View(context) {
  lateinit var editor: Editor

  private var shapes: Array<Shape?> = Array(SHAPES_LENGTH) { null }
  private var isLayout = true
  private lateinit var bitmap: Bitmap
  private var startX = 0f
  private var startY = 0f
  private var endX = 0f
  private var endY = 0f

  protected lateinit var canvas: Canvas
  
  override fun onTouchEvent(event: MotionEvent?): Boolean {
    endX = event!!.x
    endY = event.y

    when (event.action) {
      MotionEvent.ACTION_DOWN -> {
        isLayout = true
        startX = endX
        startY = endY
      }
      MotionEvent.ACTION_MOVE -> {
        invalidate()
      }
      MotionEvent.ACTION_UP -> {
        isLayout = false
        addShape(editor.getShape(canvas, startX, startY, endX, endY))
      }
    }

    invalidate()
    return true
  }

  private fun addShape(shape: Shape) {
    val index = shapes.indexOfFirst { it == null }
    if (index != -1) shapes[index] = shape
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
    if (isLayout) editor.drawLayout(canvas, startX, startY, endX, endY)
  }
}