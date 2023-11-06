package com.stbasarab.lab4.shapes

import android.graphics.Canvas
import android.graphics.PathEffect

class EllipseShape(
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
    canvas.drawOval(startX, startY, endX, endY, paint)
    setFillStyle()
    canvas.drawOval(startX, startY, endX, endY, paint)
  }
}