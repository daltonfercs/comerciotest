package com.comercio.comercioapk.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment

abstract class BaseFragment<B : ViewDataBinding> : DaggerFragment() {

    lateinit var binding: B

    abstract fun getLayout(): Int
    abstract fun initViewModel()
    abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        initObserver()
        return binding.root
    }

    fun hideKeyBoard() {
        val inputManager: InputMethodManager = view
            ?.getContext()
            ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val binder = requireView().windowToken
        inputManager.hideSoftInputFromWindow(
            binder,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}