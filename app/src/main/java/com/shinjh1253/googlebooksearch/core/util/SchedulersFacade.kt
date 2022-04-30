package com.shinjh1253.googlebooksearch.core.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulersFacade {
    /**
     * IO Thread pool scheduler
     */
    val IO: Scheduler = Schedulers.io()

    /**
     * Computation Thread pool scheduler
     */
    val COMPUTATION: Scheduler = Schedulers.computation()

    /**
     * Main Thread scheduler
     */
    val UI: Scheduler = AndroidSchedulers.mainThread()
}
