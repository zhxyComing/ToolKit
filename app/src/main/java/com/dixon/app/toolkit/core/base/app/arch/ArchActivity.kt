package com.dixon.app.toolkit.core.base.app.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dixon.app.toolkit.core.base.app.ComposeCompatActivity

open class ArchActivity<T : ViewModel>(override val viewModelClass: Class<T>) :
    ComposeCompatActivity(), IArchitecture<T> {

    override val viewModel: T by lazy {
        ViewModelProvider(this)[viewModelClass]
    }
}