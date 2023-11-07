package com.stbasarab.lab4.editors

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import com.stbasarab.lab4.shapes.LineOOShape
import com.stbasarab.lab4.shapes.Shape

class LineOOEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    val lineOO = LineOOShape(Color.BLACK, Color.TRANSPARENT, DashPathEffect(floatArrayOf(30f, 20f), 0f))
    lineOO.onTouchDown(startX, startY)
    lineOO.onTouchUp(endX, endY)
    lineOO.draw(canvas)
  }

  override fun getShape(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float): Shape {
    val lineOO = LineOOShape(Color.BLACK, Color.CYAN, null)
    lineOO.onTouchDown(startX, startY)
    lineOO.onTouchUp(endX, endY)
    return lineOO
  }
}