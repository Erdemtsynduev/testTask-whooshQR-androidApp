package com.erdemtsynduev.whooshqr.screen.scanner

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ScannerView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showResultScreen(codeBike: String?)
}