package com.matt.sample_zm.vm

import androidx.lifecycle.ViewModel

/**
 * ============================================================
 * 作 者 :    matt
 * 更新时间 ：2020/03/16 12:49
 * 描 述 ：
 * ============================================================
 */
abstract class BaseViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }
}