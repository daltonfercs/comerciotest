<?php

require '../utils/Constantes.php';
require '../conexion/Database.php';

class LoginOp{


    function _construct(){
      
    }

     public static function userLogin($usuario){
        if(LoginOp::validarExiteUsuario($usuario)){
            return USER_AUTENTICADO;
        }else{
            return USER_NO_ENCONTRADO;
        }
    }

    public static function userClave($usuario,$clave){
        if(LoginOp::validarExiteUsuarioClave($usuario,$clave)){
            return USER_CLAVE_CORRECTO;
        }else{
            return USER_CLAVE_INCORRETO;
        }
    }

    public static function validarExiteUsuarioClave($usuario,$clave){
        $consultar = "SELECT id FROM usuario WHERE usuario = ? AND clave = ?";
        $resultado = Database::getInstance()->getdb()->prepare($consultar);
        $resultado->execute(array($usuario,$clave));
        $row  = $resultado->fetch();
        $closeDb = Database::getInstance()->_destructor();
        return $row > 0;  
    }

    public static function validarExiteUsuario($usuario){
        $consultar = "SELECT id FROM usuario WHERE usuario = ?";
        $resultado = Database::getInstance()->getdb()->prepare($consultar);
        $resultado->execute(array($usuario));
        $row  = $resultado->fetch();
        $closeDb = Database::getInstance()->_destructor();
        return $row > 0;  
    }

    public function obtenerListaCategoria(){
        $consultar = "SELECT id, nombre_categoria AS nombreCategoria , imagen_categoria AS imagenCategoria FROM categoria WHERE estado_categoria = 'A'";
        $resultado = Database::getInstance()->getdb()->prepare($consultar);
        $resultado->execute();
        $tabla =$resultado->fetchAll(PDO::FETCH_ASSOC);
        $closeDb = Database::getInstance()->_destructor();
        return $tabla;
    }
   public function obtenerListaProducto(){
        $consultar = "SELECT id,nombre_producto AS nombreProducto,descripcion_producto AS descripcionProducto,imagen_producto AS imagenProducto,categoria_id AS categoriaId,precio_producto AS precioProducto FROM producto WHERE estado_producto= 'A'";
        $resultado = Database::getInstance()->getdb()->prepare($consultar);
        $resultado->execute();
        $tabla =$resultado->fetchAll(PDO::FETCH_ASSOC);
        $closeDb = Database::getInstance()->_destructor();
        return $tabla;
    }

  
}



?>