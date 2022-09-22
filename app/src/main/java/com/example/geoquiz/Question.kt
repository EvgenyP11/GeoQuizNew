package com.example.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val  textResId: Int,
                    val answer: Boolean,
                    val result: Int = 0,
                    var checkResult: Boolean = false)