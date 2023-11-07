package com.stbasarab.lab4.editors

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import com.stbasarab.lab4.shapes.LineShape
import com.stbasarab.lab4.shapes.Shape

class LineEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    var shape = LineShape(Color.BLACK, Color.TRANSPARENT, DashPathEffect(floatArrayOf(30f, 20f), 0f))
    shape.onTouchUp(endX, endY)
    shape.onTouchDown(startX, startY)
    shape.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    var shape = LineShape(Color.BLACK, Color.TRANSPARENT, null)
    shape.onTouchUp(endX, endY)
    shape.onTouchDown(startX, startY)
    return shape
  }

}