package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas

class CubeShape: LineShape() {
  private val rectangleShape = RectangleShape()
  override val name = "Куб"

  override fun draw(canvas: Canvas) {
    rectangleShape.setCustomBorder(currentBorder)
    rectangleShape.setStrokeStyle()
    setStrokeStyle()
    drawCube(canvas)
  }

  override fun drawFrame(canvas: Canvas) {
    rectangleShape.setFrameMode()
    setFrameMode()
    drawCube(canvas)
  }

  private fun drawCube(canvas: Canvas) {
    val side = (endX - startX + endY - startY) / 2
    super.drawLine(canvas, startX - side, startY - side, endX - 2 * side, endY - 2 * side)
    super.drawLine(canvas, startX + side, startY + side, endX, endY)
    super.drawLine(canvas, startX + side, startY - side, endX, endY - 2 * side)
    super.drawLine(canvas, startX - side, startY + side, endX - 2 * side, endY)
    rectangleShape.drawCenterRect(canvas, startX, startY, startX + side, startY + side)
    rectangleShape.drawCenterRect(canvas, endX - side, endY - side, endX, endY)
  }
}