package com.stbasarab.lab3.editors

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import com.stbasarab.lab3.shapes.EllipseShape
import com.stbasarab.lab3.shapes.Shape


class EllipseEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    val ellipse = EllipseShape(Color.BLACK, Color.TRANSPARENT, DashPathEffect(floatArrayOf(30f, 20f), 0f))
    ellipse.onTouchDown(startX, startY)
    ellipse.onTouchUp(endX, endY)
    ellipse.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    val ellipse = EllipseShape(Color.BLACK, Color.CYAN, null)
    ellipse.onTouchDown(startX, startY)
    ellipse.onTouchUp(endX, endY)
    return ellipse
  }
}