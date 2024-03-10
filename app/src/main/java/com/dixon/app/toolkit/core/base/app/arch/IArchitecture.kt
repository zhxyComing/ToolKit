package com.dixon.app.toolkit.core.base.app.arch

import androidx.lifecycle.ViewModel

interface IArchitecture<T : ViewModel> {

    val viewModel: T

    val viewModelClass: Class<T>
}