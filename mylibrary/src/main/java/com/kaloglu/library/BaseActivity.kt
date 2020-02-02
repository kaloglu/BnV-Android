package com.kaloglu.library

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val contentResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()

        initUserInterface()

        Toast.makeText(this, "heyt be", Toast.LENGTH_LONG).show()

    }

    protected abstract fun initUserInterface()

    protected open fun setContentView() = setContentView(contentResourceId)

}
