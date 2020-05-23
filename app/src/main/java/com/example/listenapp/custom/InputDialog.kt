package custom

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View


fun Context.inputDialog(title: String, layout: Int) =
    InputDialog(this, title, layout)

private const val DEFAULT_TEXT = "Title"

class InputDialog(context: Context, title: String = DEFAULT_TEXT, layout: Int
) : AlertDialog.Builder(context) {

    var view: View = LayoutInflater.from(getContext()).inflate(layout,null)
   open var textPositive = "OK"
   open var textNegative = "CANCEL"

    var onPositive = DialogInterface.OnClickListener { _, _ -> }
    var onNegative = DialogInterface.OnClickListener { _, _ -> }

    init {
        setTitle(title)
        setView(view)
        setPositiveButton(textPositive, onPositive)
        setNegativeButton(textNegative, onNegative)
    }

}