/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.6.49-cll-lve : Database - comerciodb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`comerciodb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `comerciodb`;

/*Table structure for table `categoria` */

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(45) DEFAULT NULL,
  `estado_categoria` char(1) DEFAULT NULL,
  `imagen_categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `categoria` */

insert  into `categoria`(`id`,`nombre_categoria`,`estado_categoria`,`imagen_categoria`) values 
(1,'Electronica','A','https://sc02.alicdn.com/kf/HTB12RuNUmzqK1RjSZFH7623CpXa6.png_50x50xz.jpg'),
(2,'Ropa','A','https://sc02.alicdn.com/kf/HTB1gUuPUkzoK1RjSZFl761i4VXaw.png_50x50xz.jpg'),
(3,'Vehiculos','A','https://sc02.alicdn.com/kf/HTB1JdWLUhTpK1RjSZR0762EwXXa6.png_50x50xz.jpg'),
(4,'Deportes','A','https://sc02.alicdn.com/kf/HTB1AkuTUgHqK1RjSZJn762NLpXa2.png_50x50xz.jpg'),
(5,'Hogar y Jardin','A','https://sc02.alicdn.com/kf/HTB1RxCkXHys3KVjSZFn760FzpXaP.png_50x50xz.jpg'),
(6,'Belleza','A','https://sc02.alicdn.com/kf/HTB1K6G0UcfpK1RjSZFO7616nFXah.png_50x50xz.jpg');

/*Table structure for table `producto` */

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(50) DEFAULT NULL,
  `descripcion_producto` varchar(255) DEFAULT NULL,
  `estado_producto` char(1) DEFAULT NULL,
  `imagen_producto` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) NOT NULL,
  `precio_producto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_producto_categoria_idx` (`categoria_id`),
  CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

/*Data for the table `producto` */

insert  into `producto`(`id`,`nombre_producto`,`descripcion_producto`,`estado_producto`,`imagen_producto`,`categoria_id`,`precio_producto`) values 
(1,'smartWatch Ecg','Mejor Smartwatch Ecg ofrece reloj inteligente de lujo de gran pantalla 4G Ip68 Gps deporte reloj inteligente Android impermeable','A','https://s.alicdn.com/@sc01/kf/H8aa7208ca872495d9b68a05e7fcc4432m.jpg_250x250xzq80.jpg',1,'50'),
(2,'Kates Auriculares','Kates auriculares inalámbricos 5,0 auriculares estéreo TWS Mini en la oreja auriculares deportes juegos de teléfonos móviles HD llamada LED de potencia pantalla F9','A','https://s.alicdn.com/@sc01/kf/H93c81f9533f34ca28b63b96233b81125G.jpg_250x250xzq80.jpg',1,'24'),
(3,'Celular MOQ','Teléfonos celulares originales','A','https://s.alicdn.com/@sc01/kf/Uc01561004da4429e805dcb5215b04b68V.jpg_250x250xzq80.jpg',1,'120'),
(4,'Profesional Audio Portatil','Profesional dispositivo de Audio portátil Vocal Cable de micrófono Vocal','A','https://s.alicdn.com/@sc01/kf/Hbaaa05b1819b4b43b3ba2033f8d4e56dc.jpg_250x250xzq80.jpg',1,'180'),
(5,'Reloj mujer pulsera',' reloj mujer pulsera Ip68 impermeable reloj inteligente para las mujeres','A','https://s.alicdn.com/@sc01/kf/Hfe701ff074234f469a5582699d98292eo.jpg_250x250xzq80.jpg',1,'65'),
(6,'Auricular Inalambrico','auricular inalámbrico portátil auriculares con caja de carga auriculares Bluetooth para iPhone Android airpods2','A','https://s.alicdn.com/@sc01/kf/H86de3a79aa5b466c922dc7646b27817eC.jpg_250x250xzq80.jpg',1,'63'),
(7,'Acolchada Capucha','Mejor precio al por mayor acolchado de invierno los hombres Puffer chaqueta con capucha acolchada chaqueta','A','https://s.alicdn.com/@sc01/kf/H62a5e574a3b3402aa0c1f374ccdfa182m.jpg_250x250xzq80.jpg',2,'36'),
(8,'Chaquera Capucha','Listo para nave de los hombres al por mayor Plus tamaño acolchado chaqueta con capucha y cordón de ajuste','A','https://s.alicdn.com/@sc01/kf/H5db918b651f240848c695b09f33aab80V.jpg_250x250xzq80.jpg',2,'32'),
(9,'Edredon Chaquera','Deporte al aire libre edredón hombre resistente al agua caliente híbrido aislamiento chaqueta de invierno para hombre ','A','https://s.alicdn.com/@sc01/kf/H43ed4e1269e24832a2954a25f684a97c3.png_250x250xzq80.jpg',2,'45'),
(10,'Chaquera Gruesa','Nueva elección de Moda hombre usa gruesa chaqueta de invierno para hombres camuflados abrigo','A','https://s.alicdn.com/@sc01/kf/He3ce00240100457d8a702f81f958dadch.jpg_250x250xzq80.jpg',2,'42'),
(11,'Cremallera','Cremallera completa poliéster con capucha cuello casual hombres chaquetas','A','https://s.alicdn.com/@sc01/kf/Hc4b76584779446768f311aa59b6b91f1v.jpg_250x250xzq80.jpg',2,'43'),
(12,'WatterProf','Outdoor Waterproof Windproof 5v USB Battery Thermal Winter Electric Battery Heated Jacket ','A','//s.alicdn.com/@sc01/kf/Ha615ecb09da245d5933f4e6748d548931.jpg_250x250xzq80.jpg',2,'46'),
(13,'Asiento Camiones','FAW asientos de camiones FAW asientos auto asientos de conductor ','A','//s.alicdn.com/@sc01/kf/HTB1tfuHm63z9KJjy0Fm760iwXXau.png_250x250xzq80.jpg',3,'230'),
(14,'Plegado','Plegado eléctrico bus puerta mecanismo bifolding city bus ','A','//s.alicdn.com/@sc01/kf/HTB1CHgeHFXXXXcKXVXXq6xXFXXXr.jpg_250x250xzq80.jpg',3,'100'),
(15,'Yuton Bus','Hecho en China yutong bus eje del chasis del ajustador de holgura brazo ','A','//s.alicdn.com/@sc01/kf/H1ae8d4252e594288bef8e5ba3047bef25.jpg_250x250xzq80.jpg',3,'80'),
(16,'Cubo Rueda','Cubo de rueda para Toyota Coaster bb20 bb40 hzb40 hzb50 hzb70 ','A','//s.alicdn.com/@sc01/kf/Hfe1e0bc84d984f61b701facc66185ad86.png_250x250xzq80.jpg',3,'90'),
(17,'Rueda','Neumático de autobús ','A','//s.alicdn.com/@sc01/kf/HTB1SsXwKVXXXXb4XFXXq6xXFXXXJ.jpg_250x250xzq80.jpg',3,'250'),
(18,'Rueda Neti','Alta calidad y precio competitivo neumáticos de autobús','A','//s.alicdn.com/@sc01/kf/HTB1rqXTV7zoK1RjSZFlq6yi4VXaQ.jpg_250x250xzq80.jpg',3,'160'),
(19,'Nuevo Equipo Chino','Nuevo equipo de China, camiseta de tenis de mesa para mujeres/hombres, camisa de tenis de mesa, Pingpong Ma L , Ding N uniformes de entrenamiento, camisetas','A','http://ae01.alicdn.com/kf/HTB1.Z6YxA9WBuNjSspeq6yz5VXav/New-Team-China-Table-Tennis-Shirt-Women-Men-Table-Tennis-Jersey-Pingpong-shirt-Ma-L-Ding.jpg_220x220xz.jpg_.webp',4,'40'),
(20,'Camiseta Tenis Mujer','Camisetas de tenis para mujer, ropa con cuello en V para deportes al aire libre, ropa de entrenamiento para correr, camisetas de manga corta para Bádminton','A','http://ae01.alicdn.com/kf/H7069bea375904931bb78c3cea294feaaU/Women-Tennis-shirts-Outdoor-sports-V-neck-clothing-Running-workout-badminton-Short-sleeves-t-shirt-tees.jpg_220x220xz.jpg_.webp',4,'20'),
(21,'Zapatillas deportista Mujer','Gran oferta 2020 EU35-44 deportes característica suela exterior blanda aliento zapatos de baile Zapatos Zapatillas de deporte para mujer zapatos de practica danza moderna zapatos de Jazz','A','//ae01.alicdn.com/kf/HTB103E5DxGYBuNjy0Fnq6x5lpXaS/Hot-Sale-2020-EU35-44-Sports-Feature-Soft-Outsole-Breath-Dance-Shoes-Sneakers-For-Woman-Practice.jpg_220x220xz.jpg_.webp',4,'20'),
(22,'Zapatillas deportista Hombre','Zapatos de moda para hombre, transpirables, transpirables, zapatillas de correr de 46, zapatillas de deporte de gran tamaño, zapatos casuales cómodos para caminar y correr 48','A','//ae01.alicdn.com/kf/H744fd604eac84c7d8d2fac86c5613773a/Fashion-Men-s-Shoes-Portable-Breathable-Running-Shoes-46-Large-Size-Sneakers-Comfortable-Walking-Jogging-Casual.jpg_220x220xz.jpg_.webp',4,'50'),
(23,'Zapatillas deportista Mixto','Zapatos Damyuan para correr, zapatos deportivos transpirables ligeros para hombre, 48 cómodas zapatillas de moda para hombre, zapatos casuales de 47','A','//ae01.alicdn.com/kf/H9058409e112e450ab0d02144f61e821dL/Damyuan-Running-Shoes-Lightweight-Breathable-Man-s-Sport-Shoes-48-Comfortable-Fashion-Men-Sneakers-47-Large.jpg_220x220xz.jpg_.webp',4,'60'),
(24,'Lentes deportista Natacion','Profesional gafas de natación con tapones para los oídos de la nariz clip Electroplate de silicona','A','//ae01.alicdn.com/kf/HTB1SMKHSpXXXXXCaXXXq6xXFXXXM/2017-Brand-New-5-Colors-Men-Women-Professional-Electroplate-Waterproof-Swim-Glasses-Anti-Fog-UV-Protection.jpg_220x220xz.jpg_.webp',4,'15'),
(25,'Soporte de papel higiénico','Soporte de papel higiénico multifuncional, impermeable, montado en la pared, Caja de papel higiénico, soporte de papel higiénico, caja de almacenamiento de papel higiénico','A','http://ae01.alicdn.com/kf/H3af020759b0347d4b230ed76450be471q/Multifunctional-toilet-paper-holder-waterproof-wall-mounted-toilet-paper-box-toilet-paper-holder-toilet-paper-storage.jpg_220x220xz.jpg_.webp',5,'48'),
(26,'Globo de 112cm gigante','Globo de 112cm gigante Mickey Minnie Mouse globo de dibujos animados en papel aluminio globo de fiesta de cumpleaños niños decoraciones para fiesta de cumpleaños regalo para niños','A','//ae01.alicdn.com/kf/Hf709f771a6a144f08df3f7c7a4e7a9ddS/112cm-Giant-Mickey-Minnie-Mouse-Balloon-Cartoon-Foil-Birthday-Party-Balloon-children-Birthday-Party-Decorations-kids.jpg_220x220xz.jpg_.webp',5,'4'),
(27,'Soporte para pasta de dientes ','Soporte para pasta de dientes LEDFRE, ventosa, soporte para pasta de dientes montado en la pared, escurridor, soporte limpiador, estante de almacenamiento Set de accesorios de baño','A','//ae01.alicdn.com/kf/H83227d1b558d430bb3b16d0318721e40L/LEDFRE-Toothpaste-holder-suction-cup-Wall-Mounted-Toothpaste-Squeezer-Holder-Cleanser-Storage-Rack-Bathroom-Accessories-Set.jpg_220x220xz.jpg_.webp',5,'28'),
(28,'Ramo de peonías','Ramo de peonías de seda rosa de 30cm, 5 cabezas grandes 4 pequeños ramo de flores artificiales para decoración del hogar, boda, novia','A','//ae01.alicdn.com/kf/HTB13HoVaQT2gK0jSZFkq6AIQFXaM/30cm-Rose-Pink-Silk-Bouquet-Peony-Artificial-Flowers-5-Big-Heads-4-Small-Bud-Bride-Wedding.jpg_220x220xz.jpg_.webp',5,'15'),
(29,'Cocina ventosa rejilla ','Cocina ventosa rejilla para escurrir para fregadero esponja soporte de almacenamiento de cocina fregadero jabonera escurridor estante accesorios de baño organizador','A','//ae01.alicdn.com/kf/H137c962561624f7490491b01519b334fN/Kitchen-Suction-Cup-Sink-Drain-Rack-Sponge-Storage-Holder-Kitchen-Sink-Soap-Rack-Drainer-Rack-Bathroom.jpg_220x220xz.jpg_.webp',5,'3'),
(30,'Acero inoxidable','Acero inoxidable, 5 estilos, molde para tortillas de huevo frito, molde para tortillas, utensilios de cocina para freír huevos, accesorios de cocina, anillos','A','//ae01.alicdn.com/kf/HTB1eUb2X6LuK1Rjy0Fhq6xpdFXaL/Stainless-Steel-5Style-Fried-Egg-Pancake-Shaper-Omelette-Mold-Mould-Frying-Egg-Cooking-Tools-Kitchen-Accessories.jpg_220x220xz.jpg_.webp',5,'11'),
(31,'Mascarilla de colágeno ','Mascarilla de colágeno hidratante dorada/alga, cuidado de ojos, 60 uds., parches antienvejecimiento, ojeras, bolsas para los ojos, elimina el cuidado de la piel','A','//ae01.alicdn.com/kf/HTB1QsbQeoGF3KVjSZFoq6zmpFXaf/Moisturizing-Gold-Seaweed-Collagen-Eye-Mask-Care-60Pcs-Anti-Aging-Patches-Dark-Circles-Eye-Bags-Remove.jpg_220x220xz.jpg_.webp',6,'10'),
(32,'Mascarilla','10 Uds = 5 pares de mascarilla de colágeno para ojos, parches para ojos para el cuidado de los ojos, ojeras, antiedad, antiarrugas, cuidado de la piel TSLM2','A','//ae01.alicdn.com/kf/Hda438859dd0c4b1cba2146846ff2b4c8P/10Pcs-5Pair-Gold-Crystal-Collagen-Eye-Mask-Eye-Patches-For-Eye-Care-Dark-Circles-Remove-Anti.jpg_220x220xz.jpg_.webp',6,'3'),
(33,'1 cepillo de limpieza ','1 cepillo de limpieza facial de silicona exfoliante efectivo para la nariz eliminación de espinillas cepillo limpieza profunda suave masajeador facial TSLM1','A','//ae01.alicdn.com/kf/H0f45af7c2b114c43ae339dd6662cec13K/1Pc-Silicone-Face-Cleansing-Brush-Effective-Nose-Exfoliator-Blackhead-Removal-Soft-Deep-Cleaning-Brush-Face-Scrub.jpg_220x220xz.jpg_.webp',6,'2'),
(34,'Mini botella perfume','Mini botella de Perfume rellenable de 5ml, atomizador en Spray portátil de aluminio, para cosméticos, botella de Perfume para viajes','A','//ae01.alicdn.com/kf/HTB1LsHGX0jvK1RjSspiq6AEqXXak/Refillable-5ml-Refillable-Mini-Perfume-Spray-Bottle-Aluminum-Spray-Atomizer-Portable-Travel-Cosmetic-Container-Perfume-Bottle.jpg_220x220xz.jpg_.webp',6,'4'),
(35,'Crema analgesica','Crema analgésica Shaolin China OPHAX adecuada para la artritis reumatoide/dolor articular/alivio del dolor de espalda ungüento de bálsamo analgésico','A','//ae01.alicdn.com/kf/H9128d086a82c4a919b777b20956984c9K/OPHAX-Chinese-Shaolin-Analgesic-Cream-Suitable-For-Rheumatoid-Arthritis-Joint-Pain-Back-Pain-Relief-Analgesic-Balm.jpg_220x220xz.jpg_.webp',6,'6'),
(36,'Diamantes ','Diamantes de imitación para uñas AB, transparentes, Blanco (SS3-SS20), fondo plano, diamantes de imitación, decoración 3D, 1440 unidades por paquete','A','//ae01.alicdn.com/kf/H2308964b10fa4cf492380b9d5ae51088Z/1440pcs-pack-AAA-shiny-nail-rhinestones-AB-Transparent-white-SS3-SS20-flat-bottom-rhinestones-3D-nail.jpg_220x220xz.jpg_.webp',6,'20');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` int(11) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `clave` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`id`,`usuario`,`clave`) values 
(1,'dalton','dalton');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
