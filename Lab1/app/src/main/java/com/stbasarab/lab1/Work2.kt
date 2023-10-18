package com.stbasarab.lab1

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class Work2: DialogFragment() {
  private var dialogListenerInterface: DialogListenerInterface? = null

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity.let {
      val builder = AlertDialog.Builder(it)
      val groups = resources.getStringArray(R.array.groups)
      var selectedGroup: Int = -1

      builder.setTitle(R.string.work2)

      builder.setSingleChoiceItems(groups, selectedGroup) { _, which ->
        selectedGroup = which
      }

      builder.setPositiveButton(R.string.yes) { _, _ ->
        if (selectedGroup != -1) {
          dialogListenerInterface?.dialogListener(R.string.work2, R.string.yes, groups[selectedGroup])
        }
      }

      builder.setNegativeButton(R.string.cancel) { _, _ ->
        dialogListenerInterface?.dialogListener(R.string.work2, R.string.cancel)
      }
      
      builder.create()
    }
  }

  fun setListener(listener: DialogListenerInterface?): Work2 {
    this.dialogListenerInterface = listener
    return this
  }
}