<?php

require '../conexion/Database.php';


class ListaCategoriaOp{


    function _construct(){

    }

    public static function obtenerCategoria(){
        try{
            $consultar = "SELECT * FROM categoria WHERE estado_categoria= 'A' ";
            $resultado = Database::getInstance()->getdb()->prepare($consultar);
			$resultado->execute();
			$tabla =$resultado->fetchAll(PDO::FETCH_ASSOC);
			$closeDb = Database::getInstance()->_destructor();
        	return $tabla;
        }catch(Exception $e){
            return null;
        }
                            
    }
    

 
}

?>