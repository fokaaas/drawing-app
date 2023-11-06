package com.stbasarab.lab3.shapes

import android.graphics.Canvas
import android.graphics.PathEffect

class RectangleShape(
  paintColor: Int,
  fillColor: Int,
  effect: PathEffect?
): Shape(
  paintColor,
  fillColor,
  effect
) {
  override fun draw(canvas: Canvas) {
    setStrokeStyle()
    canvas.drawRect(startX, startY, endX, endY, paint)
    setFillStyle()
    canvas.drawRect(startX, startY, endX, endY, paint)
  }
}