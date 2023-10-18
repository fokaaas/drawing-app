package com.stbasarab.lab2.shapes

import android.graphics.Canvas

class PointShape(paintColor: Int, fillColor: Int): Shape(paintColor, fillColor) {
  override fun draw(canvas: Canvas) {
    canvas.drawPoint(endX, endY, paint);
  }
}