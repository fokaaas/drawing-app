package com.stbasarab.lab4.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PathEffect
import kotlin.math.abs
import kotlin.math.sqrt

class CubeShape(
  paintColor: Int,
  fillColor: Int,
  effect: PathEffect?
): LineShape(paintColor, fillColor, effect) {
  private val rectangleShape = RectangleShape(paintColor, fillColor, effect)

  override fun draw(canvas: Canvas) {
    val dx = endX - startX
    val dy = endY - startY

    val side = (dx + dy) / 2
    canvas.drawRect(startX, startY, startX + side, startY + side, paint)
    canvas.drawRect(endX - side, endY - side, endX, endY, paint)
    canvas.drawLine(startX, startY, endX - side, endY - side, paint)
    canvas.drawLine(startX + side, startY + side, endX, endY, paint)
    canvas.drawLine(startX + side, startY, endX, endY - side, paint)
    canvas.drawLine(startX, startY + side, endX - side, endY, paint)
  }

  private fun drawSquare(x: Float, y: Float, delta: Float) {
    rectangleShape.onTouchDown(x, y)
  }
}