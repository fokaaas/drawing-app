package com.stbasarab.lab2.shapes

import android.graphics.Canvas

class RectangleShape(paintColor: Int, fillColor: Int): Shape(paintColor, fillColor) {
  override fun draw(canvas: Canvas) {
    setStrokeStyle()
    canvas.drawRect(startX, startY, endX, endY, paint)
    setFillStyle()
    canvas.drawRect(startX, startY, endX, endY, paint)
  }
}