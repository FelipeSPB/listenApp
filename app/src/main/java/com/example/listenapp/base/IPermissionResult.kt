package base

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat.checkSelfPermission

import custom.IContext

private const val PERMISSION_REQUEST_CODE = 11

interface IPermissionResult {
    var iPermissionRequest: IPermissionRequest?

    fun requestPermissionsResult(
        code: Int,
        permissions: Array<out String>,
        results: IntArray
    ) {
        if (code == PERMISSION_REQUEST_CODE && results.isNotEmpty()) {
            iPermissionRequest?.run {
                if (results[0] == PERMISSION_GRANTED) {
                    onPermissionGranted(permissions[0])
                } else {
                    onPermissionDenied(permissions[0])
                }
            }
        }
    }
}

interface IPermissionRequest : IContext {

    fun requestPermission(permission: String) =
        if (checkSelfPermission(activity, permission) == PERMISSION_GRANTED)
            onPermissionGranted(permission) else onRequest(permission)

    private fun onRequest(permission: String) {
        (activity as IPermissionResult).iPermissionRequest = this
        requestPermissions(activity, arrayOf(permission), PERMISSION_REQUEST_CODE)
        onPermissionRequested(permission)
    }

    fun onPermissionRequested(permission: String) {}

    fun onPermissionGranted(permission: String) {}

    fun onPermissionDenied(permission: String) {}
}
