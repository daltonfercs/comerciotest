package com.comercio.comercioapk.principal.perfil

import androidx.lifecycle.ViewModelProvider
import com.comercio.comercioapk.R
import com.comercio.comercioapk.base.BaseFragment
import com.comercio.comercioapk.databinding.FragmentPerfilBinding
import com.comercio.comercioapk.util.injectViewModel
import javax.inject.Inject


class PerfilFragment : BaseFragment<FragmentPerfilBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PerfilViewModel

    override fun getLayout(): Int {
        return R.layout.fragment_perfil
    }

    override fun initViewModel() {
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun initObserver() {

    }



}