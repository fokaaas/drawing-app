package com.stbasarab.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint


private const val STROKE_WITH = 10f

abstract class Shape(val borderColor: Int, val fillColor: Int) {
  protected var startX = 0f
  protected var startY = 0f
  protected var endX = 0f
  protected var endY = 0f

  protected val paint = Paint().apply {
    style = Paint.Style.STROKE
    color = borderColor
    isAntiAlias
    isDither
    strokeWidth = STROKE_WITH
    strokeCap = Paint.Cap.ROUND
  }

  fun setStrokeStyle() {
    paint.style = Paint.Style.STROKE
    paint.color = borderColor
    paint.pathEffect = null
  }

  fun setFillStyle() {
    paint.style = Paint.Style.FILL
    paint.color = fillColor
    paint.pathEffect = null
  }

  fun setFrameMode() {
    paint.style = Paint.Style.STROKE
    paint.color = Color.BLACK
    paint.pathEffect = DashPathEffect(floatArrayOf(30f, 20f), 0f)
  }

  fun onTouchDown(x: Float, y: Float) {
    startX = x
    startY = y
  }

  fun onTouchUp(x: Float, y: Float) {
    endX = x
    endY = y
  }

  abstract fun draw(canvas: Canvas)
  abstract fun drawFrame(canvas: Canvas)
}