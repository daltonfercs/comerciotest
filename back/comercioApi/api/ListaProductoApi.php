<?php

require '../operaciones/ListaProductoOp.php';

if($_SERVER['REQUEST_METHOD']=='GET'){
        
        $listadoProductos = ListaProductoOp::obtenerProducto();

        if($listadoProductos){
            $response_data = array();
            $response_data['error'] = false;
            $response_data['message'] =  'Cargo Correctamente';
            $response_data['listadoProducto'] =  $listadoProductos;
            echo json_encode($response_data,JSON_UNESCAPED_UNICODE);
        }else {
            $response_data['error'] = true;
            $response_data['message'] =  'Intentelo más Tarde ';
            echo json_encode($response_data,JSON_UNESCAPED_UNICODE);  
        }
   
    
}

