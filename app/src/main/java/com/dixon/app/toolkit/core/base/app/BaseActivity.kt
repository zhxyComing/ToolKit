package com.dixon.app.toolkit.core.base.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


open class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    // 提供一个空的EventBus方法，这样就不会因为没有Subscribe报错了
    // 性能影响忽略不计吧
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(myEvent: Any?) {
    }
}