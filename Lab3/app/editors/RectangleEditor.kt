package com.stbasarab.lab3.editors

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import com.stbasarab.lab3.shapes.RectangleShape
import com.stbasarab.lab3.shapes.Shape
import kotlin.math.max
import kotlin.math.min

class RectangleEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    val rectangle = RectangleShape(Color.BLACK, Color.TRANSPARENT, DashPathEffect(floatArrayOf(30f, 20f), 0f))
    val left = min(2 * startX - endX, endX)
    val top = min(2 * startY - endY, endY)
    val right = max(2 * startX - endX, endX)
    val bottom = max(2 * startY - endY, endY)
    rectangle.onTouchDown(left, top)
    rectangle.onTouchUp(right, bottom)
    rectangle.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    val rectangle = RectangleShape(Color.BLACK, Color.TRANSPARENT, null)
    val left = min(2 * startX - endX, endX)
    val top = min(2 * startY - endY, endY)
    val right = max(2 * startX - endX, endX)
    val bottom = max(2 * startY - endY, endY)
    rectangle.onTouchDown(left, top)
    rectangle.onTouchUp(right, bottom)
    return rectangle
  }
}