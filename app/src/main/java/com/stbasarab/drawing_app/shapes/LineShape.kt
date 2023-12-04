package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Color

open class LineShape: Shape(Color.BLACK, Color.TRANSPARENT) {
  override val name = "Лінія"

  override fun draw(canvas: Canvas) {
    setStrokeStyle()
    drawLine(canvas, startX, startY, endX, endY)
  }

  override fun drawFrame(canvas: Canvas) {
    setFrameMode()
    drawLine(canvas, startX, startY, endX, endY)
  }

  protected fun drawLine(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    canvas.drawLine(startX, startY, endX, endY, paint)
  }
}