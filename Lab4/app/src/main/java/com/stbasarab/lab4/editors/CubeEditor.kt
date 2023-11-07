package com.stbasarab.lab4.editors

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import com.stbasarab.lab4.shapes.CubeShape
import com.stbasarab.lab4.shapes.Shape

class CubeEditor: Editor() {
  override fun drawLayout(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float) {
    val cubeShape = CubeShape(Color.BLACK, Color.TRANSPARENT, DashPathEffect(floatArrayOf(30f, 20f), 0f))
    cubeShape.onTouchDown(startX, startY)
    cubeShape.onTouchUp(endX, endY)
    cubeShape.draw(canvas)
  }

  override fun getShape(
    canvas: Canvas,
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float
  ): Shape {
    val cubeShape = CubeShape(Color.BLACK, Color.TRANSPARENT, null)
    cubeShape.onTouchDown(startX, startY)
    cubeShape.onTouchUp(endX, endY)
    return cubeShape
  }
}