<?php

require '../operaciones/ListaCategoriaOp.php';

if($_SERVER['REQUEST_METHOD']=='GET'){
        
        $listaCategoria = ListaCategoriaOp::obtenerCategoria();

        if($listaCategoria){
            $response_data = array();
            $response_data['error'] = false;
            $response_data['message'] =  'Cargo Correctamente';
            $response_data['listadoCategoria'] =  $listaCategoria;
            echo json_encode($response_data,JSON_UNESCAPED_UNICODE);
        }else {
            $response_data['error'] = true;
            $response_data['message'] =  'Intentelo más Tarde ';
            echo json_encode($response_data,JSON_UNESCAPED_UNICODE);  
        }
   
    
}

