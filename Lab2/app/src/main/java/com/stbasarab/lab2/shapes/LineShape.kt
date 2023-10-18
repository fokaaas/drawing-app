package com.stbasarab.lab2.shapes

import android.graphics.Canvas

class LineShape(paintColor: Int, fillColor: Int): Shape(paintColor, fillColor) {
  override fun draw(canvas: Canvas) {
    canvas.drawLine(startX, startY, endX, endY, paint)
  }
}