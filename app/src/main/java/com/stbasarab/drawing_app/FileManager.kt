package com.stbasarab.drawing_app

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileManager(private val context: Context) {

  fun writeFile(fileName: String, data: String) {
    val internalStorageDir = context.filesDir
    val file = File(internalStorageDir, fileName)

    val outputStream = FileOutputStream(file)
    outputStream.write(data.toByteArray())
    outputStream.close()
  }

  fun readFile(fileName: String): String {
    val internalStorageDir = context.filesDir
    val file = File(internalStorageDir, fileName)

    val inputStream = FileInputStream(file)
    val buffer = ByteArray(1024)
    val bytesRead = inputStream.read(buffer)
    inputStream.close()

    return String(buffer, 0, bytesRead)
  }

  fun getNamesOfFiles(): Array<String>? {
    val internalStorageDir = context.filesDir
    return internalStorageDir?.list()
  }
}