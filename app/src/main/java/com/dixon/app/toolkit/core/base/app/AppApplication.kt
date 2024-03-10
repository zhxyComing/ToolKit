package com.dixon.app.toolkit.core.base.app

import com.dixon.app.toolkit.core.util.Ln

class AppApplication : BaseApplication() {

    override fun doInitDepend() {
        Ln.i("AppApplication", "initDepend: ${Thread.currentThread()}")
//        initARouter()
    }

    override fun doInitDependAsync() {
        Ln.i("AppApplication", "initDependAsync: ${Thread.currentThread()}")
    }
}