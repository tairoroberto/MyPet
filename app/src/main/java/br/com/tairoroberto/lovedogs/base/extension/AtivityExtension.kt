package br.com.tairoroberto.lovedogs.base.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import br.com.tairoroberto.lovedogs.settings.ConfigDAO
import br.com.tairoroberto.lovedogs.settings.Configuracoes


/**
 * Created by tairo on 9/2/17.
 */
fun AppCompatActivity.showProgress(form: View, progressBar: ProgressBar, show: Boolean) {
    val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

    form.visibility = if (show) View.GONE else View.VISIBLE
    form.animate()
            .setDuration(shortAnimTime)
            .alpha((if (show) 0 else 1).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    form.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

    progressBar.visibility = if (show) View.VISIBLE else View.GONE
    progressBar.animate()
            .setDuration(shortAnimTime)
            .alpha((if (show) 1 else 0).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    progressBar.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
}

fun Activity.showSnackBarError(view: View, msg: String) {
    val snackbar: Snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            .setAction("OK", null)
    snackbar.view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
    snackbar.show()
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

fun Activity.getConfig(): Configuracoes? {
    val configDao = getConfigDAO()
    var config = configDao.getById(1)
    if( config?.id?.toInt() == 0) {
        configDao.insert(Configuracoes())
        config = configDao.getById(1)
    }
    return config
}

fun Activity.getConfigDAO(): ConfigDAO {
    return ConfigDAO(applicationContext)
}