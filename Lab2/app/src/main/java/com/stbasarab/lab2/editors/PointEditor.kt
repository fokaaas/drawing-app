package com.stbasarab.lab2.editors

import android.graphics.Canvas
import android.graphics.Color
import com.stbasarab.lab2.shapes.PointShape
import com.stbasarab.lab2.shapes.Shape

class PointEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    // doesn't include layout
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    val point = PointShape(Color.BLACK, Color.TRANSPARENT)
    point.onTouchUp(endX, endY)
    return point
  }
}