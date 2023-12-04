package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Color

class EllipseShape: Shape(Color.BLACK, Color.CYAN) {
  override val name = "Еліпс"

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