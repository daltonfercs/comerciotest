package com.comercio.comercioapk.di.activity

import com.comercio.comercioapk.carrito.CarritoActivity
import com.comercio.comercioapk.di.viewmodel.ViewModelModule
import com.comercio.comercioapk.login.LoginActivity
import com.comercio.comercioapk.principal.PrincipalActivity
import com.comercio.comercioapk.principal.categorias.CategoriasFragment
import com.comercio.comercioapk.principal.inicio.InicioFragment
import com.comercio.comercioapk.principal.perfil.PerfilFragment
import com.comercio.comercioapk.principal.productos.ProductosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributePrincipalActivity(): PrincipalActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributeCategoriasFragment(): CategoriasFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributeInicioFragment(): InicioFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributePerfilFragment(): PerfilFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributeProductosFragment(): ProductosFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun contributeCarritoActivity(): CarritoActivity

}