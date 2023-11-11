package com.stbasarab.lab4.shapes

import android.graphics.Canvas

open class LineShape(borderColor: Int, fillColor: Int): Shape(borderColor, fillColor) {
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