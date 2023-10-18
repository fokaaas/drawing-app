package com.stbasarab.lab2.editors

import android.graphics.Canvas
import android.graphics.Color
import com.stbasarab.lab2.shapes.EllipseShape
import com.stbasarab.lab2.shapes.Shape
import kotlin.math.max
import kotlin.math.min

class EllipseEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    val ellipse = EllipseShape(Color.BLUE, Color.TRANSPARENT)
    val left = min(2 * startX - endX, endX)
    val top = min(2 * startY - endY, endY)
    val right = max(2 * startX - endX, endX)
    val bottom = max(2 * startY - endY, endY)
    ellipse.onTouchDown(left, top)
    ellipse.onTouchUp(right, bottom)
    ellipse.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    val ellipse = EllipseShape(Color.BLACK, Color.TRANSPARENT)
    val left = min(2 * startX - endX, endX)
    val top = min(2 * startY - endY, endY)
    val right = max(2 * startX - endX, endX)
    val bottom = max(2 * startY - endY, endY)
    ellipse.onTouchDown(left, top)
    ellipse.onTouchUp(right, bottom)
    return ellipse
  }
}