package custom

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class VerticalRecycler @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        setHasFixedSize(true)
    }
}