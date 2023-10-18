package com.stbasarab.lab2.editors

import android.graphics.Canvas
import android.graphics.Color
import com.stbasarab.lab2.shapes.RectangleShape
import com.stbasarab.lab2.shapes.Shape

class RectangleEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    val rectangle = RectangleShape(Color.BLUE, Color.TRANSPARENT)
    rectangle.onTouchDown(startX, startY)
    rectangle.onTouchUp(endX, endY)
    rectangle.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    val rectangle = RectangleShape(Color.BLACK, Color.CYAN)
    rectangle.onTouchDown(startX, startY)
    rectangle.onTouchUp(endX, endY)
    return rectangle
  }
}