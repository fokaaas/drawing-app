package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Color

class RectangleShape: Shape(Color.BLACK, Color.TRANSPARENT) {
  override val name = "Прямокутник"

  override fun draw(canvas: Canvas) {
    setStrokeStyle()
    drawCenterRect(canvas, startX, startY, endX, endY)
  }

  override fun drawFrame(canvas: Canvas) {
    setFrameMode()
    drawCenterRect(canvas, startX, startY, endX, endY)
  }

  fun drawCenterRect(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    val centerX = 2 * startX - endX
    val centerY = 2 * startY - endY
    canvas.drawRect(centerX, centerY, endX, endY, paint)
  }
}