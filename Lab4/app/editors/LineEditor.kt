package com.stbasarab.lab3.editors

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import com.stbasarab.lab3.shapes.LineShape
import com.stbasarab.lab3.shapes.Shape

class LineEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    var shape = LineShape(Color.BLACK, Color.TRANSPARENT, DashPathEffect(floatArrayOf(30f, 20f), 0f))
    shape.onTouchUp(startX, startY)
    shape.onTouchDown(endX, endY)
    shape.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    var line = LineShape(Color.BLACK, Color.TRANSPARENT, null)
    line.onTouchUp(startX, startY)
    line.onTouchDown(endX, endY)
    return line
  }

}