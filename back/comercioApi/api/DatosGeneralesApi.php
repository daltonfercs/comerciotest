<?php
require '../operaciones/DatosGeneralesOp.php';

if($_SERVER['REQUEST_METHOD']=='GET'){
   
   /*Lista Respuesta de Json */
    $listadoCategoria = DatosGeneralesOp::obtenerListaCategoria();
	$listadoProducto = DatosGeneralesOp::obtenerListaProducto();

    try {
      		$response_data = array();
            $response_data['error'] = false;
            $response_data['message'] =  'Datos Correctos';
            $response_data['listadoCategoria'] = $listadoCategoria;
		    $response_data['listadoProducto'] = $listadoProducto;
            echo json_encode($response_data,JSON_UNESCAPED_UNICODE);
    } catch (Exception $e) {
            $response_data['error'] = true;
            $response_data['message'] =  'Datos Incorrectos';
            echo json_encode($response_data,JSON_UNESCAPED_UNICODE);
    }


}




?>