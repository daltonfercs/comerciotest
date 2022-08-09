package com.comercio.comercioapk.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.comercio.comercioapk.carrito.CarritoViewModel
import com.comercio.comercioapk.di.base.ViewModelKey
import com.comercio.comercioapk.login.LoginViewModel
import com.comercio.comercioapk.principal.PrincipalViewModel
import com.comercio.comercioapk.principal.categorias.CategoriasViewModel
import com.comercio.comercioapk.principal.inicio.InicioViewModel
import com.comercio.comercioapk.principal.perfil.PerfilFragment
import com.comercio.comercioapk.principal.perfil.PerfilViewModel
import com.comercio.comercioapk.principal.productos.ProductosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    /*Actividad*/
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PrincipalViewModel::class)
    abstract fun bindPrincipalViewModel(viewModel: PrincipalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriasViewModel::class)
    abstract fun bindCategoriasViewModel(viewModel: CategoriasViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InicioViewModel::class)
    abstract fun bindInicioViewModel(viewModel: InicioViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PerfilViewModel::class)
    abstract fun bindPerfilViewModel(viewModel: PerfilViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductosViewModel::class)
    abstract fun bindProductosViewModel(viewModel: ProductosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CarritoViewModel::class)
    abstract fun bindCarritoViewModel(viewModel: CarritoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}