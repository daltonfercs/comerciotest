package pe.farmacias.peruanas.cajeroexpress.principal.categorias.model

data class CategoriasUi(
    val categoriaId: Long,
    val categoriaNombre: String,
    val imagen: String,
    var items: String
)