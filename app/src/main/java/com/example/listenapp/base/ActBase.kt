package base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup


open class ActBase(open val layout: Int = 0) : AppCompatActivity()/*, IPermissionResult*/ {

    open val view: Any? = null

    companion object {
        @JvmStatic
        lateinit var currentActivity: AppCompatActivity

        /*@JvmStatic
        var exceptionHandler = ExceptionHandler()*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Thread.setDefaultUncaughtExceptionHandler(exceptionHandler)
        intent?.extras?.onExtras()
        if (layout != 0) {
            setContentView(layout)
            ((window.decorView.rootView as ViewGroup).getChildAt(0) as ViewGroup).onView()
        } else if (view is View) {
            setContentView(view as View)
            (view as ViewGroup).onView()
        }
    }

    override fun onResume() {
        super.onResume()
        currentActivity = this
    }

    open fun Bundle.onExtras() {}

    open fun ViewGroup.onView() {}

    /*override var iPermissionRequest: IPermissionRequest? = null

    override fun onRequestPermissionsResult(
        code: Int,
        permissions: Array<out String>,
        results: IntArray
    ) = requestPermissionsResult(code, permissions, results)*/
}