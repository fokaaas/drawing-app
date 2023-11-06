package com.stbasarab.lab3.shapes

import android.graphics.Canvas
import android.graphics.PathEffect

class PointShape(
  paintColor: Int,
  fillColor: Int,
  effect: PathEffect?
): Shape(
  paintColor,
  fillColor,
  effect
) {
  override fun draw(canvas: Canvas) {
    canvas.drawPoint(endX, endY, paint);
  }
}