package wisata.travel.sumbar.base

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import wisata.travel.sumbar.R

open class BaseActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("NewApi")
    fun showSettingsDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle(getString(R.string.permission))
        builder.setMessage(HtmlCompat.fromHtml(getString(R.string.app_name), HtmlCompat.FROM_HTML_MODE_LEGACY))
        builder.setPositiveButton(getString(R.string.check_permission)) { dialog, _ ->
            dialog.cancel()
            openSettings()
        }
        builder.show()
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", packageName, null)
        startActivity(intent)
    }

    open fun getActivity(): BaseActivity? {
        return this
    }

    open fun hideKeyboard() {
        val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val v: View = getActivity()?.currentFocus!!
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

}