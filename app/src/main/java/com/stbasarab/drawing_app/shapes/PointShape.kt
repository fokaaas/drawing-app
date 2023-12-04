package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Color

class PointShape: Shape(Color.BLACK, Color.TRANSPARENT) {
  override val name = "Крапка"

  override fun draw(canvas: Canvas) {
    setStrokeStyle()
    canvas.drawPoint(endX, endY, paint)
  }

  override fun drawFrame(canvas: Canvas) {
    setFrameMode()
  }
}