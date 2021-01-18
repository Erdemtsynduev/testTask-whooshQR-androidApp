package com.erdemtsynduev.whooshqr.screen.result

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.erdemtsynduev.whooshqr.R
import kotlinx.android.synthetic.main.activity_result.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ResultActivity : MvpAppCompatActivity(), ResultView {

    @InjectPresenter
    lateinit var resultPresenter: ResultPresenter

    @ProvidePresenter
    fun provideResultPresenter(): ResultPresenter {
        val dataBike = intent.getStringExtra(INTENT_DATA_BIKE)
        return ResultPresenter(dataBike)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }

    override fun showDataResponse(status: String?, comments: String?) {
        activity_result_status_text.text = status
        activity_result_comment_text.text = comments
    }

    override fun showError(errorText: String?) {
        Toast.makeText(this, "Error request = $errorText", Toast.LENGTH_LONG).show()
    }

    override fun startProgressBar() {
        toggleProgressVisibility(true)
    }

    override fun stopProgressBar() {
        toggleProgressVisibility(false)
    }

    private fun toggleProgressVisibility(show: Boolean) {
        activity_result_progress_bar.visibility = if (show) View.VISIBLE else View.GONE
    }

    companion object {
        const val INTENT_DATA_BIKE = "INTENT_DATA_BIKE"
    }
}