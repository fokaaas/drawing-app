package com.stbasarab.lab1

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class Work1: DialogFragment() {
  private var dialogListenerInterface: DialogListenerInterface? = null

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity.let {
      val builder = AlertDialog.Builder(it)

      builder.setTitle(R.string.work1)

      builder.setPositiveButton(R.string.next) { _, _ ->
        dialogListenerInterface?.dialogListener(R.string.work1, R.string.next)
      }

      builder.setNegativeButton(R.string.cancel) { _, _ ->
        dialogListenerInterface?.dialogListener(R.string.work1, R.string.cancel)
      }

      builder.create()
    }
  }

  fun setListener(listener: DialogListenerInterface?): Work1 {
    this.dialogListenerInterface = listener
    return this
  }
}