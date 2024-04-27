-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-04-2024 a las 17:26:43
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

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
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `tb_clientes`
--

INSERT INTO `tb_clientes` (`idCliente`, `nombre`, `primerApellido`, `segundoApellido`, `password`, `documentoIdentificacion`, `fechaNacimiento`, `telefono`, `correo`, `calle`, `numeroCalle`, `piso`, `letra`, `fechaCreacion`, `genero`, `estado`) VALUES
(2, 'a', 'a', 'a', 0x0c8d4314f973a69aff3bf0060fa16ec3, '49154724K', '1987-05-19 00:00:00', '634583869', 'aitorvazgar@gmail.com', 'Castilla la vieja', 8, 5, 'D', '2024-04-17 00:00:00', 1, 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_estadotarjeta`
--

CREATE TABLE `tb_estadotarjeta` (
  `idEstado` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_estadotarjeta`
--

INSERT INTO `tb_estadotarjeta` (`idEstado`, `nombre`) VALUES
(1, 'Activa'),
(2, 'Bloqueada'),
(3, 'Inactiva'),
(4, 'Cancelada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_estadotransaccion`
--

CREATE TABLE `tb_estadotransaccion` (
  `idEstado` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_genero`
--

INSERT INTO `tb_genero` (`idGenero`, `nombre`) VALUES
(1, 'HOMBRE'),
(2, 'MUJER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_marcatarjeta`
--

CREATE TABLE `tb_marcatarjeta` (
  `idMarca` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_marcatarjeta`
--

INSERT INTO `tb_marcatarjeta` (`idMarca`, `nombre`) VALUES
(1, 'Diners'),
(2, 'Mastercard'),
(3, 'Visa'),
(4, 'Magna'),
(5, 'American Express'),
(6, 'Diversa'),
(7, 'Enjoy Card'),
(8, 'Ripley'),
(9, 'Tarjeta Unimarc'),
(10, 'Otras');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_paises`
--

CREATE TABLE `tb_paises` (
  `idPais` int(11) NOT NULL,
  `iso` char(2) DEFAULT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  `codPais` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `idEstadoTarjeta` int(11) NOT NULL,
  `idTipoTarjeta` int(11) NOT NULL,
  `idMarcaTarjeta` int(11) NOT NULL,
  `numeroTarjeta` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_tipocuenta`
--

CREATE TABLE `tb_tipocuenta` (
  `idTipoCuenta` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_tipocuenta`
--

INSERT INTO `tb_tipocuenta` (`idTipoCuenta`, `nombre`) VALUES
(1, 'Cuenta Corriente'),
(2, 'Cuenta nomina'),
(3, 'Cuenta ahorro'),
(4, 'Cuenta online'),
(5, 'Cuenta remunerada'),
(6, 'Cuenta infantil'),
(7, 'Cuenta joven'),
(8, 'Cuenta mancomunada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_tipoentidad`
--

CREATE TABLE `tb_tipoentidad` (
  `idTipo` int(11) NOT NULL,
  `nombreTipo` varchar(50) NOT NULL,
  `codigo` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_tipoentidad`
--

INSERT INTO `tb_tipoentidad` (`idTipo`, `nombreTipo`, `codigo`) VALUES
(1, 'Banco', 0),
(2, 'Banco', 1),
(3, 'Caja de ahorros', 2),
(4, 'Cooperativa de credito', 3),
(5, 'Entidades de pago', 4),
(6, 'Entidades de pago', 6),
(7, 'Entidades de pago', 8),
(8, 'Establecimiento de compra venta de moneda', 17),
(9, 'Sociedades de tasacion', 43),
(10, 'Sociedades de tasacion', 44),
(11, 'Sociedades de garantia reciproca', 98);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_tipoerror`
--

CREATE TABLE `tb_tipoerror` (
  `idTipoError` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Estructura de tabla para la tabla `tb_tipomoneda`
--

CREATE TABLE `tb_tipomoneda` (
  `idTipoMoneda` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `pais` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_tipomoneda`
--

INSERT INTO `tb_tipomoneda` (`idTipoMoneda`, `nombre`, `pais`) VALUES
(1, 'Peso', 'Argentina'),
(2, 'Dolar', 'Australia'),
(3, 'Boliviano', 'Bolivia'),
(4, 'Real', 'Brasil'),
(5, 'Dolar', 'Canada'),
(6, 'Peso', 'Chile'),
(7, 'Yuan', 'China'),
(8, 'Peso', 'Colombia'),
(9, 'Corona', 'Dinamarca'),
(10, 'Dirham', 'Emiratos Arabes Unidos'),
(11, 'Dolar', 'Estados Unidos'),
(12, 'Dolar', 'Hong Kong'),
(13, 'Rupia', 'India'),
(14, 'Dinar', 'Iraq'),
(15, 'Yen', 'Japon'),
(16, 'Nuevo Peso', 'Mexico'),
(17, 'Corona', 'Noruega'),
(18, 'Dolar', 'Nueva Zelanda'),
(19, 'Guarani', 'Paraguay'),
(20, 'Nuevo Sol', 'Peru'),
(21, 'Libra ', 'Reino Unido'),
(22, 'Dolar', 'Singapur'),
(23, 'Rand', 'Sudafrica'),
(24, 'Corona', 'Suecia'),
(25, 'Franco', 'Suiza'),
(26, 'Bath', 'Tailandia'),
(27, 'Dolar', 'Taiwan'),
(28, 'Bolivar ', 'Venezuela'),
(29, 'Euro', 'U.E.M.'),
(30, 'ORO', 'Operaciones con pago en oro'),
(31, 'Corona', 'Republica Checa'),
(32, 'Sequel', 'Israel'),
(33, 'Won', 'Corea del sur'),
(34, 'Riyal', 'Arabia Saudita'),
(35, 'UA', 'Unidad de Cuenta Banco Interamericano de Desarrollo'),
(36, 'Oz(Au)', 'Onza Troy Oro'),
(37, 'Oz(Ag)', 'Onza Troy Plata'),
(38, 'U.R.', 'Unidad reajustable'),
(39, 'I.V.P. ', 'Pesos chilenos reajustables según el índice valor promedio'),
(40, 'U.F.', 'Pesos chilenos reajustables según la unidad de fomento'),
(41, 'ETCA', 'Expresado en moneda extranjera'),
(42, 'ETCM', 'Expresado en moneda extranjera'),
(43, 'Otras', 'Otras monedas de países no'),
(44, 'U.T.M.', 'Unidad Tributaria Mensual'),
(45, 'T.C.', 'Pesos chilenos reajustables según el tipo de cambio del dólar'),
(46, 'IPC', 'Pesos reajustables por la variación del IPC ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_tipotarjeta`
--

CREATE TABLE `tb_tipotarjeta` (
  `idTipoTarjeta` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_tipotarjeta`
--

INSERT INTO `tb_tipotarjeta` (`idTipoTarjeta`, `nombre`) VALUES
(1, 'Tarjeta de debito'),
(2, 'tarjeta de credito'),
(3, 'Tarjeta revolvente'),
(4, 'Tarjeta de prepago'),
(5, 'Tarjeta contactless'),
(6, 'Tarjeta de un solo uso');

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
  `codigoSPEI` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  ADD KEY `genero` (`genero`);

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
-- Indices de la tabla `tb_estadotarjeta`
--
ALTER TABLE `tb_estadotarjeta`
  ADD PRIMARY KEY (`idEstado`);

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
-- Indices de la tabla `tb_marcatarjeta`
--
ALTER TABLE `tb_marcatarjeta`
  ADD PRIMARY KEY (`idMarca`);

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
  ADD KEY `idTipoTarjeta` (`idTipoTarjeta`),
  ADD KEY `idEstadoTarjeta` (`idEstadoTarjeta`),
  ADD KEY `idMarcaTarjeta` (`idMarcaTarjeta`);

--
-- Indices de la tabla `tb_tipocuenta`
--
ALTER TABLE `tb_tipocuenta`
  ADD PRIMARY KEY (`idTipoCuenta`);

--
-- Indices de la tabla `tb_tipoentidad`
--
ALTER TABLE `tb_tipoentidad`
  ADD PRIMARY KEY (`idTipo`);

--
-- Indices de la tabla `tb_tipoerror`
--
ALTER TABLE `tb_tipoerror`
  ADD PRIMARY KEY (`idTipoError`);

--
-- Indices de la tabla `tb_tipomoneda`
--
ALTER TABLE `tb_tipomoneda`
  ADD PRIMARY KEY (`idTipoMoneda`);

--
-- Indices de la tabla `tb_tipotarjeta`
--
ALTER TABLE `tb_tipotarjeta`
  ADD PRIMARY KEY (`idTipoTarjeta`);

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
-- AUTO_INCREMENT de la tabla `tb_estadotarjeta`
--
ALTER TABLE `tb_estadotarjeta`
  MODIFY `idEstado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
-- AUTO_INCREMENT de la tabla `tb_marcatarjeta`
--
ALTER TABLE `tb_marcatarjeta`
  MODIFY `idMarca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
-- AUTO_INCREMENT de la tabla `tb_tipocuenta`
--
ALTER TABLE `tb_tipocuenta`
  MODIFY `idTipoCuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tb_tipoentidad`
--
ALTER TABLE `tb_tipoentidad`
  MODIFY `idTipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `tb_tipoerror`
--
ALTER TABLE `tb_tipoerror`
  MODIFY `idTipoError` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tb_tipomoneda`
--
ALTER TABLE `tb_tipomoneda`
  MODIFY `idTipoMoneda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `tb_tipotarjeta`
--
ALTER TABLE `tb_tipotarjeta`
  MODIFY `idTipoTarjeta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  ADD CONSTRAINT `tb_cuentas_ibfk_3` FOREIGN KEY (`tipoMoneda`) REFERENCES `tb_tipomoneda` (`idTipoMoneda`),
  ADD CONSTRAINT `tb_cuentas_ibfk_4` FOREIGN KEY (`idTarjeta`) REFERENCES `tb_tarjeta` (`idTarjeta`);

--
-- Filtros para la tabla `tb_tarjeta`
--
ALTER TABLE `tb_tarjeta`
  ADD CONSTRAINT `tb_tarjeta_ibfk_1` FOREIGN KEY (`idEstadoTarjeta`) REFERENCES `tb_estadotarjeta` (`idEstado`),
  ADD CONSTRAINT `tb_tarjeta_ibfk_2` FOREIGN KEY (`idTipoTarjeta`) REFERENCES `tb_tipotarjeta` (`idTipoTarjeta`),
  ADD CONSTRAINT `tb_tarjeta_ibfk_3` FOREIGN KEY (`idMarcaTarjeta`) REFERENCES `tb_marcatarjeta` (`idMarca`);

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
