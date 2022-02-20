package com.adedom.myfood.base

import androidx.activity.ComponentActivity
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

abstract class BaseActivity : ComponentActivity(), DIAware {

    override val di: DI by closestDI()
}