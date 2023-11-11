package com.stbasarab.lab4.shapes

import android.graphics.Canvas

class EllipseShape(paintColor: Int, fillColor: Int): Shape(paintColor, fillColor) {
  override fun draw(canvas: Canvas) {
    setStrokeStyle()
    canvas.drawOval(startX, startY, endX, endY, paint)
    setFillStyle()
    canvas.drawOval(startX, startY, endX, endY, paint)
  }

  override fun drawFrame(canvas: Canvas) {
    setFrameMode()
    canvas.drawOval(startX, startY, endX, endY, paint)
  }
}