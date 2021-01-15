package com.erdemtsynduev.whooshqr.screen.scanner

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.erdemtsynduev.whooshqr.R
import com.erdemtsynduev.whooshqr.screen.result.ResultActivity
import com.erdemtsynduev.whooshqr.screen.result.ResultActivity.Companion.INTENT_DATA_BIKE
import com.huawei.hms.hmsscankit.RemoteView
import com.huawei.hms.ml.scan.HmsScan
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ScannerActivity : MvpAppCompatActivity(), ScannerView {

    @InjectPresenter
    lateinit var scannerPresenter: ScannerPresenter

    @ProvidePresenter
    fun provideScannerPresenter(): ScannerPresenter {
        return ScannerPresenter()
    }

    var frameLayout: FrameLayout? = null
    var remoteView: RemoteView? = null

    var screenWidth = 0
    var screenHeight = 0

    private val scanFrameSize = 240

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_scanner)

        frameLayout = findViewById(R.id.rim)

        //1. Obtain the screen density to calculate the viewfinder's rectangle.
        val dm = resources.displayMetrics
        val density = dm.density
        //2. Obtain the screen size.
        screenWidth = resources.displayMetrics.widthPixels
        screenHeight = resources.displayMetrics.heightPixels
        val scanFrameSize = (scanFrameSize * density).toInt()
        //3. Calculate the viewfinder's rectangle, which in the middle of the layout.
        //Set the scanning area. (Optional. Rect can be null. If no settings are specified, it will be located in the middle of the layout.)
        val rect = Rect()
        rect.left = screenWidth / 2 - scanFrameSize / 2
        rect.right = screenWidth / 2 + scanFrameSize / 2
        rect.top = screenHeight / 2 - scanFrameSize / 2
        rect.bottom = screenHeight / 2 + scanFrameSize / 2

        //Initialize the RemoteView instance, and set callback for the scanning result.
        remoteView = RemoteView.Builder().setContext(this).setBoundingBox(rect)
            .setFormat(HmsScan.ALL_SCAN_TYPE).build()
        // Subscribe to the scanning result callback event.

        remoteView?.setOnResultCallback { result -> //Check the result.
            if (result != null && result.isNotEmpty() && result[0] != null && !TextUtils.isEmpty(
                    result[0].getOriginalValue()
                )
            ) {
                scannerPresenter.checkQRData(result[0].getOriginalValue())
            }
        }

        // Load the customized view to the activity.
        remoteView?.onCreate(savedInstanceState)
        val params = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        frameLayout?.addView(remoteView, params)
    }

    override fun onStart() {
        super.onStart()
        remoteView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        remoteView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        remoteView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        remoteView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        remoteView?.onDestroy()
    }

    override fun showResultScreen(codeBike: String?) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(INTENT_DATA_BIKE, codeBike)
        startActivity(intent)
    }
}