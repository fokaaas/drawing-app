package com.stbasarab.lab2.editors

import android.graphics.Canvas
import android.graphics.Color
import com.stbasarab.lab2.shapes.LineShape
import com.stbasarab.lab2.shapes.Shape

class LineEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    var shape = LineShape(Color.BLUE, Color.TRANSPARENT)
    shape.onTouchUp(startX, startY)
    shape.onTouchDown(endX, endY)
    shape.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    var line = LineShape(Color.BLACK, Color.TRANSPARENT)
    line.onTouchUp(startX, startY)
    line.onTouchDown(endX, endY)
    return line
  }

}