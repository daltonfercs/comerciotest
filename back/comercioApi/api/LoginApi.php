<?php

require '../operaciones/LoginOp.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
    if (isset($_POST['usuario']) &&  isset($_POST['clave']) ) {
        
        $usuario = $_POST['usuario'];
        $clave = $_POST['clave'];
        $login = LoginOp::userLogin($usuario);
        if($login == USER_AUTENTICADO){
            $loginClave = LoginOp::userClave($usuario,$clave);
            if($loginClave == USER_CLAVE_CORRECTO){
                $listadoCategoria = LoginOp::obtenerListaCategoria();
                $listadoProducto = LoginOp::obtenerListaProducto();
                $response_data = array();
                $response_data['error']= false;
                $response_data['message']='Login Correctamente!';
                $response_data['listadoProducto'] = $listadoProducto;
                $response_data['listadoCategoria'] = $listadoCategoria;
                echo json_encode($response_data);
            }else if ($loginClave == USER_CLAVE_INCORRETO){
                $response_data = array();
                $response_data['error']= true;
                $response_data['message']='Clave incorrecta';
                echo json_encode($response_data);
            }

            /*$listadoCategoria = LoginOp::obtenerListaCategoria();
            $listadoProducto = LoginOp::obtenerListaProducto();
            $response_data = array();
            $response_data['error']= false;
            $response_data['message']='Login Correctamente!';
            $response_data['listadoProducto'] = $listadoProducto;
            $response_data['listadoCategoria'] = $listadoCategoria;
            echo json_encode($response_data);*/
        }else if($login == USER_NO_ENCONTRADO){
            $response_data = array();
            $response_data['error']= true;
            $response_data['message']='Usuario no existe';
            echo json_encode($response_data);
        }
    }else {
        $response['error'] = true;
        $response['message'] = 'Requiere Parametros Necesarios!';
         echo json_encode($response);
    }
}




?>