package com.comercio.comercioapk.principal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.comercio.comercioapk.R
import com.comercio.comercioapk.base.BaseActivity
import com.comercio.comercioapk.base.Resource
import com.comercio.comercioapk.base.Status
import com.comercio.comercioapk.carrito.CarritoActivity
import com.comercio.comercioapk.databinding.ActivityPrincipalBinding
import com.comercio.comercioapk.di.viewmodel.ViewModelProviderFactory
import com.comercio.comercioapk.principal.categorias.CategoriasFragment
import com.comercio.comercioapk.principal.inicio.InicioFragment
import com.comercio.comercioapk.principal.perfil.PerfilFragment
import com.comercio.comercioapk.principal.productos.ProductosFragment
import com.comercio.comercioapk.principal.productos.listener.ProductosListener
import com.comercio.comercioapk.util.injectViewModel
import com.vincent.bottomnavigationbar.BottomItem
import kotlinx.android.synthetic.main.toolbar_home.view.*
import timber.log.Timber
import javax.inject.Inject

class PrincipalActivity : BaseActivity<ActivityPrincipalBinding>() , ProductosListener{

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: PrincipalViewModel
    private var mPackageName: String? = null
    private var mBnbDefaultList: MutableList<BottomItem>? = null


    override fun getLayout(): Int {
        return R.layout.activity_principal
    }

    override fun initViewModel() {
        viewModel = injectViewModel(providerFactory)
        initStartOnClik()
        initViewModelCarrito()
    }

    private fun initStartOnClik() {
        binding.include.imageViewCart.setOnClickListener {
            startActivity(Intent(this@PrincipalActivity, CarritoActivity::class.java))
        }
    }


    private fun initView() {
        mPackageName = applicationInfo.packageName
        val item1 = BottomItem()
        item1.text = "Inicio"
        item1.iconResID = resources.getIdentifier("ic_tint_bag", "drawable", mPackageName)
        item1.activeBgResID = R.drawable.bg_bottom_navi_selected
        item1.inactiveBgResID = R.drawable.bg_bottom_navi_normal
        binding.bottomBar.addItem(item1)
        mBnbDefaultList?.add(item1)

        val item2 = BottomItem()
        item2.text = "Productos"
        item2.iconResID = resources.getIdentifier("ic_tint_book", "drawable", mPackageName)
        item2.activeBgResID = R.drawable.bg_bottom_navi_selected
        item2.inactiveBgResID = R.drawable.bg_bottom_navi_normal
        binding.bottomBar.addItem(item2)
        mBnbDefaultList?.add(item2)

        val item3 = BottomItem()
        item3.text = "Categorias"
        item3.iconResID = resources.getIdentifier("ic_tint_cart", "drawable", mPackageName)
        item3.activeBgResID = R.drawable.bg_bottom_navi_selected
        item3.inactiveBgResID = R.drawable.bg_bottom_navi_normal
        binding.bottomBar.addItem(item3)
        mBnbDefaultList?.add(item3)

        val item4 = BottomItem()
        item4.text = "Perfil"
        item4.iconResID = resources.getIdentifier("ic_tint_list", "drawable", mPackageName)
        item4.activeBgResID = R.drawable.bg_bottom_navi_selected
        item4.inactiveBgResID = R.drawable.bg_bottom_navi_normal
        binding.bottomBar.addItem(item4)
        mBnbDefaultList?.add(item4)


        binding.bottomBar.initialize()

    }


    override fun initObserver() {
        viewModel.posicion.observe(this, Observer { posicion ->
            initFragment(posicion)
        })
    }
    private fun initObtenerCarritoConteo(result: Resource<String>?) {
        when (result?.status) {
            Status.SUCCESS -> {
                binding.include.textViewNumero.text = result.data
            }
            Status.LOADING -> Timber.d("CARGANDO")
            Status.ERROR -> Timber.d("ERROR TRAER DB")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initBottomBar()
    }

    private fun initBottomBar() {
        binding.bottomBar.addOnSelectedListener { _, newPosition ->
            viewModel.initPosicionAdapter(newPosition)
        }
    }

    private fun initFragment(posicion: Int?) {
        when (posicion) {
            0 -> initPrimerFragmento()
            1 -> initProductosFragmento()
            2 -> initCategoriasFragmento()
            3 -> initPerfilFragmento()
            else -> Timber.d("Default::Error")
        }
    }

    private fun initPerfilFragmento() {
        replaceFragment(PerfilFragment())
    }

    private fun initCategoriasFragmento() {
        replaceFragment(CategoriasFragment())
    }

    private fun initProductosFragmento() {
        replaceFragment(ProductosFragment())
    }

    private fun initPrimerFragmento() {
        replaceFragment(InicioFragment())
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
        transaction.commit()
    }

    override fun onClickAgregarCarrito() {
        initViewModelCarrito()
    }
    private fun initViewModelCarrito() {
        viewModel.carritoConteo.observe(this, Observer { res ->
            initObtenerCarritoConteo(res)
        })
    }

}