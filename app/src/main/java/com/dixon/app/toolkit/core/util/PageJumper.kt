package com.dixon.app.toolkit.core.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dixon.app.toolkit.core.page.PageType
import com.dixon.app.toolkit.core.page.home.HomeActivity
import com.dixon.app.toolkit.core.page.picofday.PicOfDayActivity
import com.dixon.app.toolkit.core.page.translate.TranslateActivity

/**
 * 页面跳转
 */

const val PAGE_EXTRA = "page_extra"

/**
 * 跳转指定的Class页面
 * Class涉及强耦合，一般不允许开发者直接使用，所以私有
 */
private fun Any.jumpPage(clazz: Class<*>, putExtraAct: (Bundle.() -> Unit)? = null) = asContext {
    val intent = Intent(this, clazz).apply {
        putExtra(PAGE_EXTRA, Bundle().apply {
            putExtraAct?.invoke(this)
        })
    }
    startActivity(intent)
}

/**
 * Any 转 Context
 */
private fun Any.asContext(action: Context.() -> Unit) {
    val context = when (this) {
        is Fragment -> this.context
        is android.app.Fragment -> this.activity
        is Activity -> this
        is Context -> this
        else -> null
    } ?: return
    action.invoke(context)
}

/**
 * 通过指定页面类型来跳转页面
 */
fun Any.jumpPage(pageType: PageType, putExtraAct: (Bundle.() -> Unit)? = null) =
    when (pageType) {
        PageType.HOME -> jumpPage(HomeActivity::class.java, putExtraAct)
        PageType.TRANSLATE -> jumpPage(TranslateActivity::class.java, putExtraAct)
        PageType.NOTE -> jumpPage(HomeActivity::class.java, putExtraAct)
        PageType.PIC_OF_DAY -> jumpPage(PicOfDayActivity::class.java, putExtraAct)
    }