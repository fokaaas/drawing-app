package com.stbasarab.drawing_app

interface MainActivityInterface {
  fun getEditorViewInstance(): MyEditor
  fun onHighlightElement(index: Int, isHighlight: Boolean)
  fun onRemoveShapeFromRow(index: Int)
}