package com.stbasarab.lab4.shapes

import android.graphics.Canvas
import android.graphics.PathEffect

const val RADIUS = 50

class LineOOShape(
  paintColor: Int,
  fillColor: Int,
  effect: PathEffect?
): LineShape(paintColor, fillColor, effect) {
  private val ellipseShape = EllipseShape(paintColor, fillColor, effect)

  override fun draw(canvas: Canvas) {
    super.draw(canvas)
    drawLineCircle(canvas, startX, startY)
    drawLineCircle(canvas, endX, endY)
  }

  private fun drawLineCircle(canvas: Canvas, lineX: Float, lineY: Float) {
    ellipseShape.onTouchDown(lineX - RADIUS, lineY - RADIUS)
    ellipseShape.onTouchUp(lineX + RADIUS, lineY + RADIUS)
    ellipseShape.draw(canvas)
  }
}