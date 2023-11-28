package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas

const val RADIUS = 50

class CircleLineShape(borderColor: Int, fillColor: Int): LineShape(borderColor, fillColor) {
  private val ellipseShape = EllipseShape(borderColor, fillColor)

  override fun draw(canvas: Canvas) {
    super.draw(canvas)
    setEllipseCoordinates(startX, startY)
    ellipseShape.draw(canvas)
    setEllipseCoordinates(endX, endY)
    ellipseShape.draw(canvas)
  }

  override fun drawFrame(canvas: Canvas) {
    super.drawFrame(canvas)
    setEllipseCoordinates(startX, startY)
    ellipseShape.drawFrame(canvas)
    setEllipseCoordinates(endX, endY)
    ellipseShape.drawFrame(canvas)
  }

  private fun setEllipseCoordinates(lineX: Float, lineY: Float) {
    ellipseShape.onTouchDown(lineX - RADIUS, lineY - RADIUS)
    ellipseShape.onTouchUp(lineX + RADIUS, lineY + RADIUS)
  }
}