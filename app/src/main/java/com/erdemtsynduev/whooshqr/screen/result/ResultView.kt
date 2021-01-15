package com.erdemtsynduev.whooshqr.screen.result

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ResultView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showDataResponse(status: String?, comments: String?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(errorText: String?)
}