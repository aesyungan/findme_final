

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";



-- Base de datos: `findme`
--
CREATE DATABASE `findme` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `findme`;

DELIMITER $$
--
-- Funciones
--
CREATE DEFINER=`findme`@`%` FUNCTION `fn_user_dis`( id int ) RETURNS varchar(90) CHARSET latin1
RETURN(
SELECT u.nombre FROM usuario_dispositivo as ud inner JOIN usuario as u on u.id_usuario=ud.id_usuario inner join dispositivo as d on d.id_dispositivo=ud.id_dispositivo where ud.propietario=1 and ud.id_usuario=id

)$$

CREATE DEFINER=`findme`@`%` FUNCTION `fn_user_diss`( id int ) RETURNS varchar(120) CHARSET latin1
RETURN(
SELECT CONCAT(u.nombre, ' ', u.apellido) FROM usuario_dispositivo as ud inner JOIN usuario as u on u.id_usuario=ud.id_usuario inner join dispositivo as d on d.id_dispositivo=ud.id_dispositivo where ud.propietario=1 and ud.id_dispositivo=id
)$$

CREATE DEFINER=`findme`@`%` FUNCTION `fn_user_foto`( id int ) RETURNS varchar(120) CHARSET latin1
RETURN(
SELECT u.foto FROM usuario_dispositivo as ud inner JOIN usuario as u on u.id_usuario=ud.id_usuario inner join dispositivo as d on d.id_dispositivo=ud.id_dispositivo where ud.propietario=1 and ud.id_dispositivo=id
)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispositivo`
--

CREATE TABLE IF NOT EXISTS `dispositivo` (
  `id_dispositivo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `imei` varchar(40) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `fecha_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id_dispositivo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=18 ;

--
-- Volcado de datos para la tabla `dispositivo`
--

INSERT INTO `dispositivo` (`id_dispositivo`, `descripcion`, `imei`, `latitud`, `longitud`, `altura`, `fecha_update`) VALUES
(1, 's3mini alex yungan', '123', -1.6071659, -78.63139990000002, 14, '2016-10-07 00:00:00'),
(2, 's4 Ing Jorge Delgado', '213214134', -1.68519053, -78.63879691, 12, '2016-11-18 03:09:38'),
(6, 'j5 prueba', '12121', -50.91372332660818, -72.90089406073093, 12, '2016-10-25 11:59:48'),
(11, 'htc one 12', '123454321', -1.68511747, -78.63900634, 12, '2016-10-03 02:23:40'),
(15, 'Galaxy J5', '352141071388934', -1.68499783, -78.63873863, 12, '2016-10-29 20:23:22'),
(16, 'Estuardo Cajilema_LG Spirit', '358300063128476', 0, 0, 0, '2016-10-10 22:30:53'),
(17, 'Galaxy J5', '352141071388934', 0, 0, 0, '2016-10-14 18:47:41');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nick` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `password` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `foto` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=18 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `nick`, `password`, `foto`) VALUES
(1, 'Alex', 'Yungan', 'alex', 'alex', 'http://findme.webcindario.com/img/user2.jpg'),
(2, 'Ana', 'Perez', 'ana', 'ana', 'http://findme.webcindario.com/img/images.jpg'),
(3, 'juan', 'Lopez', 'juan', 'juan', 'http://findme.webcindario.com/img/user.jpg'),
(15, 'carlitos', 'Paredes12', 'carlitos', '12355', 'carlitos.png'),
(16, 'Estuardo', 'Cajilema ', 'Estuardo ', '12345', 'http://findme.webcindario.com/img/user.jpg'),
(17, '', '', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_dispositivo`
--

CREATE TABLE IF NOT EXISTS `usuario_dispositivo` (
  `id_usuario_dispositivo` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `id_dispositivo` int(11) DEFAULT NULL,
  `propietario` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id_usuario_dispositivo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=18 ;

--
-- Volcado de datos para la tabla `usuario_dispositivo`
--

INSERT INTO `usuario_dispositivo` (`id_usuario_dispositivo`, `id_usuario`, `id_dispositivo`, `propietario`) VALUES
(1, 1, 1, 0),
(2, 1, 2, 1),
(6, 2, 6, 1),
(14, 16, 16, 1),
(15, 1, 16, 0),
(16, 17, 15, 1),
(17, 1, 6, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
