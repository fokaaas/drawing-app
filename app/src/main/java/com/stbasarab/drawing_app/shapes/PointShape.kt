package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas

class PointShape(borderColor: Int, fillColor: Int): Shape(borderColor, fillColor) {
  override val name = "Крапка"

  override fun draw(canvas: Canvas) {
    setStrokeStyle()
    canvas.drawPoint(endX, endY, paint)
  }

  override fun drawFrame(canvas: Canvas) {
    setFrameMode()
  }
}