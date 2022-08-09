package com.comercio.comercioapk.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import com.comercio.comercioapk.R
import com.comercio.comercioapk.base.BaseActivity
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.Status
import com.comercio.comercioapk.databinding.ActivityLoginBinding
import com.comercio.comercioapk.di.viewmodel.ViewModelProviderFactory
import com.comercio.comercioapk.login.service.LoginResponse
import com.comercio.comercioapk.login.service.Usuario
import com.comercio.comercioapk.principal.PrincipalActivity
import com.comercio.comercioapk.util.injectViewModel
import timber.log.Timber
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: LoginViewModel


    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel() {
        viewModel = injectViewModel(providerFactory)
        initView()
    }

    private fun initView() {
        binding.loginButton.setOnClickListener {
            initValidateUsuario()
        }
    }

    private fun initValidateSesion(it: Resource<LoginResponse>?) {
        when (it?.status) {
            Status.LOADING -> Timber.d("Cargando")
            Status.SUCCESS -> {
                Timber.d("Cargo Correctament los servicios")
                if (it.data != null) {
                    if (it.data.error) {
                        mostrarMensaje(it.data.message)
                        return
                    }
                    viewModel.initGuardarDB(
                        it.data.listadoProducto,
                        it.data.listadoCategoria
                    )
                    iniciarActividadPrincipal()
                }
            }
            Status.ERROR -> Timber.d("Ocurrio algun problema")
        }
    }

    private fun iniciarActividadPrincipal() {
        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
    }

    override fun initObserver() {
        viewModel.obtenerListaProductos.observe(this, Observer {
            initValidateSesion(it)
        })
    }

    private fun initValidateUsuario() {
        val usuario = binding.editextUsuario.text.toString()
        val clave = binding.editextClave.text.toString()
        if (usuario.isEmpty()) {
            mostrarMensaje("Llenar usuario!!")
            return
        }
        if (clave.isEmpty()) {
            mostrarMensaje("Llenar clave!!")
            return
        }
        val usuarioModel = Usuario(usuario, clave)
        viewModel.obteniendoUsuario(usuarioModel)
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(applicationContext, mensaje, Toast.LENGTH_SHORT).show()
    }

}