-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2024 a las 17:40:03
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_banco`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_clientes`
--

CREATE TABLE `tb_clientes` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `primerApellido` varchar(30) NOT NULL,
  `segundoApellido` varchar(30) NOT NULL,
  `password` varbinary(200) NOT NULL,
  `documentoIdentificacion` varchar(9) NOT NULL,
  `fechaNacimiento` datetime NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `correo` varchar(80) DEFAULT NULL,
  `calle` varchar(180) NOT NULL,
  `numeroCalle` int(11) DEFAULT NULL,
  `piso` int(11) DEFAULT NULL,
  `letra` char(2) DEFAULT NULL,
  `fechaCreacion` datetime NOT NULL,
  `genero` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `idImagen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tb_clientes`
--

INSERT INTO `tb_clientes` (`idCliente`, `nombre`, `primerApellido`, `segundoApellido`, `password`, `documentoIdentificacion`, `fechaNacimiento`, `telefono`, `correo`, `calle`, `numeroCalle`, `piso`, `letra`, `fechaCreacion`, `genero`, `estado`, `idImagen`) VALUES
(2, 'a', 'a', 'a', 0x0c8d4314f973a69aff3bf0060fa16ec3, '49154724K', '1987-05-19 00:00:00', '634583869', 'aitorvazgar@gmail.com', 'Castilla la vieja', 8, 5, 'D', '2024-04-17 00:00:00', 1, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_cuentas`
--

CREATE TABLE `tb_cuentas` (
  `idCuenta` bigint(20) NOT NULL,
  `idTitular` int(11) NOT NULL,
  `codPais` int(11) NOT NULL,
  `digitoControlPais` int(2) NOT NULL,
  `entidad` int(4) NOT NULL,
  `oficina` int(4) NOT NULL,
  `digitoControlCuenta` int(2) NOT NULL,
  `numCuenta` int(10) NOT NULL,
  `saldo` double(18,6) NOT NULL,
  `tipoMoneda` int(11) NOT NULL,
  `idTarjeta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_estadotransaccion`
--

CREATE TABLE `tb_estadotransaccion` (
  `idEstado` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_estadotransaccion`
--

INSERT INTO `tb_estadotransaccion` (`idEstado`, `nombre`) VALUES
(1, 'En proceso'),
(2, 'En espera'),
(3, 'Finalizada'),
(4, 'Finalizada con error'),
(5, 'Cancelada'),
(6, 'Devuelta'),
(7, 'Devuelta finalizada'),
(8, 'Devuelta con error');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_estadousuario`
--

CREATE TABLE `tb_estadousuario` (
  `idEstadoUsuario` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_estadousuario`
--

INSERT INTO `tb_estadousuario` (`idEstadoUsuario`, `nombre`) VALUES
(1, 'ACTIVO'),
(2, 'INACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_genero`
--

CREATE TABLE `tb_genero` (
  `idGenero` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_genero`
--

INSERT INTO `tb_genero` (`idGenero`, `nombre`) VALUES
(1, 'HOMBRE'),
(2, 'MUJER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_imagen`
--

CREATE TABLE `tb_imagen` (
  `idImagen` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `imagen` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_paises`
--

CREATE TABLE `tb_paises` (
  `idPais` int(11) NOT NULL,
  `iso` char(2) DEFAULT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  `codPais` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_paises`
--

INSERT INTO `tb_paises` (`idPais`, `iso`, `nombre`, `codPais`) VALUES
(1, 'ES', 'España', 142800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_tarjeta`
--

CREATE TABLE `tb_tarjeta` (
  `idTarjeta` int(11) NOT NULL,
  `estadoTarjeta` varchar(100) NOT NULL,
  `tipoTarjeta` varchar(100) NOT NULL,
  `marcaTarjeta` varchar(100) NOT NULL,
  `numeroTarjeta` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_tipoerror`
--

CREATE TABLE `tb_tipoerror` (
  `idTipoError` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_tipoerror`
--

INSERT INTO `tb_tipoerror` (`idTipoError`, `nombre`) VALUES
(1, 'Error en la conexión'),
(2, 'Error de banco salida'),
(3, 'Error interno'),
(4, 'Error desconocido'),
(5, 'Error sin fondos'),
(6, 'Sin error'),
(7, 'Sin error');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_transaccion`
--

CREATE TABLE `tb_transaccion` (
  `idTransaccion` bigint(20) NOT NULL,
  `idClienteSalida` int(11) NOT NULL,
  `idBanco` int(11) NOT NULL,
  `cantidad` double(18,6) NOT NULL,
  `idEstado` int(11) NOT NULL,
  `idTipoError` int(11) DEFAULT NULL,
  `concepto` varchar(150) DEFAULT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_clientes`
--
ALTER TABLE `tb_clientes`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `documentoIdentificacion` (`documentoIdentificacion`),
  ADD KEY `estado` (`estado`),
  ADD KEY `genero` (`genero`),
  ADD KEY `idImagen` (`idImagen`);

--
-- Indices de la tabla `tb_cuentas`
--
ALTER TABLE `tb_cuentas`
  ADD PRIMARY KEY (`idCuenta`),
  ADD KEY `idTitular` (`idTitular`),
  ADD KEY `tipoMoneda` (`tipoMoneda`),
  ADD KEY `idTarjeta` (`idTarjeta`),
  ADD KEY `codPais` (`codPais`);

--
-- Indices de la tabla `tb_estadotransaccion`
--
ALTER TABLE `tb_estadotransaccion`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `tb_estadousuario`
--
ALTER TABLE `tb_estadousuario`
  ADD PRIMARY KEY (`idEstadoUsuario`);

--
-- Indices de la tabla `tb_genero`
--
ALTER TABLE `tb_genero`
  ADD PRIMARY KEY (`idGenero`);

--
-- Indices de la tabla `tb_imagen`
--
ALTER TABLE `tb_imagen`
  ADD PRIMARY KEY (`idImagen`);

--
-- Indices de la tabla `tb_paises`
--
ALTER TABLE `tb_paises`
  ADD PRIMARY KEY (`idPais`);

--
-- Indices de la tabla `tb_tarjeta`
--
ALTER TABLE `tb_tarjeta`
  ADD PRIMARY KEY (`idTarjeta`),
  ADD UNIQUE KEY `numeroTarjeta` (`numeroTarjeta`),
  ADD KEY `idTipoTarjeta` (`tipoTarjeta`),
  ADD KEY `idEstadoTarjeta` (`estadoTarjeta`),
  ADD KEY `idMarcaTarjeta` (`marcaTarjeta`);

--
-- Indices de la tabla `tb_tipoerror`
--
ALTER TABLE `tb_tipoerror`
  ADD PRIMARY KEY (`idTipoError`);

--
-- Indices de la tabla `tb_transaccion`
--
ALTER TABLE `tb_transaccion`
  ADD PRIMARY KEY (`idTransaccion`),
  ADD KEY `idTipoError` (`idTipoError`),
  ADD KEY `idEstado` (`idEstado`),
  ADD KEY `idBanco` (`idBanco`),
  ADD KEY `idClienteSalida` (`idClienteSalida`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_clientes`
--
ALTER TABLE `tb_clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_cuentas`
--
ALTER TABLE `tb_cuentas`
  MODIFY `idCuenta` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_estadotransaccion`
--
ALTER TABLE `tb_estadotransaccion`
  MODIFY `idEstado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tb_estadousuario`
--
ALTER TABLE `tb_estadousuario`
  MODIFY `idEstadoUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_genero`
--
ALTER TABLE `tb_genero`
  MODIFY `idGenero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tb_imagen`
--
ALTER TABLE `tb_imagen`
  MODIFY `idImagen` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_paises`
--
ALTER TABLE `tb_paises`
  MODIFY `idPais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=242;

--
-- AUTO_INCREMENT de la tabla `tb_tarjeta`
--
ALTER TABLE `tb_tarjeta`
  MODIFY `idTarjeta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_tipoerror`
--
ALTER TABLE `tb_tipoerror`
  MODIFY `idTipoError` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tb_transaccion`
--
ALTER TABLE `tb_transaccion`
  MODIFY `idTransaccion` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_clientes`
--
ALTER TABLE `tb_clientes`
  ADD CONSTRAINT `tb_clientes_ibfk_1` FOREIGN KEY (`estado`) REFERENCES `tb_estadousuario` (`idEstadoUsuario`),
  ADD CONSTRAINT `tb_clientes_ibfk_2` FOREIGN KEY (`genero`) REFERENCES `tb_genero` (`idGenero`);

--
-- Filtros para la tabla `tb_cuentas`
--
ALTER TABLE `tb_cuentas`
  ADD CONSTRAINT `tb_cuentas_ibfk_1` FOREIGN KEY (`idTitular`) REFERENCES `tb_clientes` (`idCliente`),
  ADD CONSTRAINT `tb_cuentas_ibfk_2` FOREIGN KEY (`codPais`) REFERENCES `tb_paises` (`idPais`),
  ADD CONSTRAINT `tb_cuentas_ibfk_4` FOREIGN KEY (`idTarjeta`) REFERENCES `tb_tarjeta` (`idTarjeta`);

--
-- Filtros para la tabla `tb_imagen`
--
ALTER TABLE `tb_imagen`
  ADD CONSTRAINT `tb_imagen_ibfk_1` FOREIGN KEY (`idImagen`) REFERENCES `tb_clientes` (`idImagen`);

--
-- Filtros para la tabla `tb_transaccion`
--
ALTER TABLE `tb_transaccion`
  ADD CONSTRAINT `tb_transaccion_ibfk_1` FOREIGN KEY (`idClienteSalida`) REFERENCES `tb_clientes` (`idCliente`),
  ADD CONSTRAINT `tb_transaccion_ibfk_2` FOREIGN KEY (`idBanco`) REFERENCES `tb_clientes` (`idCliente`),
  ADD CONSTRAINT `tb_transaccion_ibfk_3` FOREIGN KEY (`idEstado`) REFERENCES `tb_estadotransaccion` (`idEstado`),
  ADD CONSTRAINT `tb_transaccion_ibfk_5` FOREIGN KEY (`idTipoError`) REFERENCES `tb_tipoerror` (`idTipoError`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
