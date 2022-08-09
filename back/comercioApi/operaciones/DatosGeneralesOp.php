<?php

require '../conexion/Database.php';



class DatosGeneralesOp{

    
    function _construct(){
       
    }

    public static function obtenerListaCategoria(){
        $consultar = "SELECT id, nombre_categoria AS nombreCategoria , imagen_categoria AS imagenCategoria FROM categoria WHERE estado_categoria = 'A'";
        $resultado = Database::getInstance()->getdb()->prepare($consultar);
        $resultado->execute();
        $tabla =$resultado->fetchAll(PDO::FETCH_ASSOC);
        $closeDb = Database::getInstance()->_destructor();
        return $tabla;
    }
   public static function obtenerListaProducto(){
        $consultar = "SELECT id,nombre_producto AS nombreProducto,descripcion_producto AS descripcionProducto,imagen_producto AS imagenProducto,categoria_id AS categoriaId,precio_producto AS precioProducto FROM producto WHERE estado_producto= 'A'";
        $resultado = Database::getInstance()->getdb()->prepare($consultar);
        $resultado->execute();
        $tabla =$resultado->fetchAll(PDO::FETCH_ASSOC);
        $closeDb = Database::getInstance()->_destructor();
        return $tabla;
    }

}




?>