package com.kaloglu.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val contentResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()

        initUserInterface()

    }

    protected abstract fun initUserInterface()

    protected open fun setContentView() = setContentView(contentResourceId)

}
