-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-04-2024 a las 15:54:57
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
  `fechaNacimiento` datetime NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `genero` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `nombre` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_paises`
--

INSERT INTO `tb_paises` (`idPais`, `iso`, `nombre`) VALUES
(1, 'AF', 'Afganistán'),
(2, 'AX', 'Islas Gland'),
(3, 'AL', 'Albania'),
(4, 'DE', 'Alemania'),
(5, 'AD', 'Andorra'),
(6, 'AO', 'Angola'),
(7, 'AI', 'Anguilla'),
(8, 'AQ', 'Antártida'),
(9, 'AG', 'Antigua y Barbuda'),
(10, 'AN', 'Antillas Holandesas'),
(11, 'SA', 'Arabia Saudí'),
(12, 'DZ', 'Argelia'),
(13, 'AR', 'Argentina'),
(14, 'AM', 'Armenia'),
(15, 'AW', 'Aruba'),
(16, 'AU', 'Australia'),
(17, 'AT', 'Austria'),
(18, 'AZ', 'Azerbaiyán'),
(19, 'BS', 'Bahamas'),
(20, 'BH', 'Bahréin'),
(21, 'BD', 'Bangladesh'),
(22, 'BB', 'Barbados'),
(23, 'BY', 'Bielorrusia'),
(24, 'BE', 'Bélgica'),
(25, 'BZ', 'Belice'),
(26, 'BJ', 'Benin'),
(27, 'BM', 'Bermudas'),
(28, 'BT', 'Bhután'),
(29, 'BO', 'Bolivia'),
(30, 'BA', 'Bosnia y Herzegovina'),
(31, 'BW', 'Botsuana'),
(32, 'BV', 'Isla Bouvet'),
(33, 'BR', 'Brasil'),
(34, 'BN', 'Brunéi'),
(35, 'BG', 'Bulgaria'),
(36, 'BF', 'Burkina Faso'),
(37, 'BI', 'Burundi'),
(38, 'CV', 'Cabo Verde'),
(39, 'KY', 'Islas Caimán'),
(40, 'KH', 'Camboya'),
(41, 'CM', 'Camerún'),
(42, 'CA', 'Canadá'),
(43, 'CF', 'República Centroafricana'),
(44, 'TD', 'Chad'),
(45, 'CZ', 'República Checa'),
(46, 'CL', 'Chile'),
(47, 'CN', 'China'),
(48, 'CY', 'Chipre'),
(49, 'CX', 'Isla de Navidad'),
(50, 'VA', 'Ciudad del Vaticano'),
(51, 'CC', 'Islas Cocos'),
(52, 'CO', 'Colombia'),
(53, 'KM', 'Comoras'),
(54, 'CD', 'República Democrática del Congo'),
(55, 'CG', 'Congo'),
(56, 'CK', 'Islas Cook'),
(57, 'KP', 'Corea del Norte'),
(58, 'KR', 'Corea del Sur'),
(59, 'CI', 'Costa de Marfil'),
(60, 'CR', 'Costa Rica'),
(61, 'HR', 'Croacia'),
(62, 'CU', 'Cuba'),
(63, 'DK', 'Dinamarca'),
(64, 'DM', 'Dominica'),
(65, 'DO', 'República Dominicana'),
(66, 'EC', 'Ecuador'),
(67, 'EG', 'Egipto'),
(68, 'SV', 'El Salvador'),
(69, 'AE', 'Emiratos Árabes Unidos'),
(70, 'ER', 'Eritrea'),
(71, 'SK', 'Eslovaquia'),
(72, 'SI', 'Eslovenia'),
(73, 'ES', 'España'),
(74, 'UM', 'Islas ultramarinas de Estados Unidos'),
(75, 'US', 'Estados Unidos'),
(76, 'EE', 'Estonia'),
(77, 'ET', 'Etiopía'),
(78, 'FO', 'Islas Feroe'),
(79, 'PH', 'Filipinas'),
(80, 'FI', 'Finlandia'),
(81, 'FJ', 'Fiyi'),
(82, 'FR', 'Francia'),
(83, 'GA', 'Gabón'),
(84, 'GM', 'Gambia'),
(85, 'GE', 'Georgia'),
(86, 'GS', 'Islas Georgias del Sur y Sandwich del Sur'),
(87, 'GH', 'Ghana'),
(88, 'GI', 'Gibraltar'),
(89, 'GD', 'Granada'),
(90, 'GR', 'Grecia'),
(91, 'GL', 'Groenlandia'),
(92, 'GP', 'Guadalupe'),
(93, 'GU', 'Guam'),
(94, 'GT', 'Guatemala'),
(95, 'GF', 'Guayana Francesa'),
(96, 'GN', 'Guinea'),
(97, 'GQ', 'Guinea Ecuatorial'),
(98, 'GW', 'Guinea-Bissau'),
(99, 'GY', 'Guyana'),
(100, 'HT', 'Haití'),
(101, 'HM', 'Islas Heard y McDonald'),
(102, 'HN', 'Honduras'),
(103, 'HK', 'Hong Kong'),
(104, 'HU', 'Hungría'),
(105, 'IN', 'India'),
(106, 'ID', 'Indonesia'),
(107, 'IR', 'Irán'),
(108, 'IQ', 'Iraq'),
(109, 'IE', 'Irlanda'),
(110, 'IS', 'Islandia'),
(111, 'IL', 'Israel'),
(112, 'IT', 'Italia'),
(113, 'JM', 'Jamaica'),
(114, 'JP', 'Japón'),
(115, 'JO', 'Jordania'),
(116, 'KZ', 'Kazajstán'),
(117, 'KE', 'Kenia'),
(118, 'KG', 'Kirguistán'),
(119, 'KI', 'Kiribati'),
(120, 'KW', 'Kuwait'),
(121, 'LA', 'Laos'),
(122, 'LS', 'Lesotho'),
(123, 'LV', 'Letonia'),
(124, 'LB', 'Líbano'),
(125, 'LR', 'Liberia'),
(126, 'LY', 'Libia'),
(127, 'LI', 'Liechtenstein'),
(128, 'LT', 'Lituania'),
(129, 'LU', 'Luxemburgo'),
(130, 'MO', 'Macao'),
(131, 'MK', 'ARY Macedonia'),
(132, 'MG', 'Madagascar'),
(133, 'MY', 'Malasia'),
(134, 'MW', 'Malawi'),
(135, 'MV', 'Maldivas'),
(136, 'ML', 'Malí'),
(137, 'MT', 'Malta'),
(138, 'FK', 'Islas Malvinas'),
(139, 'MP', 'Islas Marianas del Norte'),
(140, 'MA', 'Marruecos'),
(141, 'MH', 'Islas Marshall'),
(142, 'MQ', 'Martinica'),
(143, 'MU', 'Mauricio'),
(144, 'MR', 'Mauritania'),
(145, 'YT', 'Mayotte'),
(146, 'MX', 'México'),
(147, 'FM', 'Micronesia'),
(148, 'MD', 'Moldavia'),
(149, 'MC', 'Mónaco'),
(150, 'MN', 'Mongolia'),
(151, 'MS', 'Montserrat'),
(152, 'MZ', 'Mozambique'),
(153, 'MM', 'Myanmar'),
(154, 'NA', 'Namibia'),
(155, 'NR', 'Nauru'),
(156, 'NP', 'Nepal'),
(157, 'NI', 'Nicaragua'),
(158, 'NE', 'Níger'),
(159, 'NG', 'Nigeria'),
(160, 'NU', 'Niue'),
(161, 'NF', 'Isla Norfolk'),
(162, 'NO', 'Noruega'),
(163, 'NC', 'Nueva Caledonia'),
(164, 'NZ', 'Nueva Zelanda'),
(165, 'OM', 'Omán'),
(166, 'NL', 'Países Bajos'),
(167, 'PK', 'Pakistán'),
(168, 'PW', 'Palau'),
(169, 'PS', 'Palestina'),
(170, 'PA', 'Panamá'),
(171, 'PG', 'Papúa Nueva Guinea'),
(172, 'PY', 'Paraguay'),
(173, 'PE', 'Perú'),
(174, 'PN', 'Islas Pitcairn'),
(175, 'PF', 'Polinesia Francesa'),
(176, 'PL', 'Polonia'),
(177, 'PT', 'Portugal'),
(178, 'PR', 'Puerto Rico'),
(179, 'QA', 'Qatar'),
(180, 'GB', 'Reino Unido'),
(181, 'RE', 'Reunión'),
(182, 'RW', 'Ruanda'),
(183, 'RO', 'Rumania'),
(184, 'RU', 'Rusia'),
(185, 'EH', 'Sahara Occidental'),
(186, 'SB', 'Islas Salomón'),
(187, 'WS', 'Samoa'),
(188, 'AS', 'Samoa Americana'),
(189, 'KN', 'San Cristóbal y Nevis'),
(190, 'SM', 'San Marino'),
(191, 'PM', 'San Pedro y Miquelón'),
(192, 'VC', 'San Vicente y las Granadinas'),
(193, 'SH', 'Santa Helena'),
(194, 'LC', 'Santa Lucía'),
(195, 'ST', 'Santo Tomé y Príncipe'),
(196, 'SN', 'Senegal'),
(197, 'CS', 'Serbia y Montenegro'),
(198, 'SC', 'Seychelles'),
(199, 'SL', 'Sierra Leona'),
(200, 'SG', 'Singapur'),
(201, 'SY', 'Siria'),
(202, 'SO', 'Somalia'),
(203, 'LK', 'Sri Lanka'),
(204, 'SZ', 'Suazilandia'),
(205, 'ZA', 'Sudáfrica'),
(206, 'SD', 'Sudán'),
(207, 'SE', 'Suecia'),
(208, 'CH', 'Suiza'),
(209, 'SR', 'Surinam'),
(210, 'SJ', 'Svalbard y Jan Mayen'),
(211, 'TH', 'Tailandia'),
(212, 'TW', 'Taiwán'),
(213, 'TZ', 'Tanzania'),
(214, 'TJ', 'Tayikistán'),
(215, 'IO', 'Territorio Británico del Océano Índico'),
(216, 'TF', 'Territorios Australes Franceses'),
(217, 'TL', 'Timor Oriental'),
(218, 'TG', 'Togo'),
(219, 'TK', 'Tokelau'),
(220, 'TO', 'Tonga'),
(221, 'TT', 'Trinidad y Tobago'),
(222, 'TN', 'Túnez'),
(223, 'TC', 'Islas Turcas y Caicos'),
(224, 'TM', 'Turkmenistán'),
(225, 'TR', 'Turquía'),
(226, 'TV', 'Tuvalu'),
(227, 'UA', 'Ucrania'),
(228, 'UG', 'Uganda'),
(229, 'UY', 'Uruguay'),
(230, 'UZ', 'Uzbekistán'),
(231, 'VU', 'Vanuatu'),
(232, 'VE', 'Venezuela'),
(233, 'VN', 'Vietnam'),
(234, 'VG', 'Islas Vírgenes Británicas'),
(235, 'VI', 'Islas Vírgenes de los Estados Unidos'),
(236, 'WF', 'Wallis y Futuna'),
(237, 'YE', 'Yemen'),
(238, 'DJ', 'Yibuti'),
(239, 'ZM', 'Zambia'),
(240, 'ZW', 'Zimbabue');

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
(5, 'Error sin fondos');

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
-- Estructura de tabla para la tabla `tb_tipotransaccion`
--

CREATE TABLE `tb_tipotransaccion` (
  `idTipo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_tipotransaccion`
--

INSERT INTO `tb_tipotransaccion` (`idTipo`, `nombre`) VALUES
(1, 'Salida'),
(2, 'Entrada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_transaccion`
--

CREATE TABLE `tb_transaccion` (
  `idTransaccion` bigint(20) NOT NULL,
  `idClienteSalida` int(11) NOT NULL,
  `idBanco` int(11) NOT NULL,
  `cantidad` double(18,6) NOT NULL,
  `idTipo` int(11) NOT NULL,
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
-- Indices de la tabla `tb_tipotransaccion`
--
ALTER TABLE `tb_tipotransaccion`
  ADD PRIMARY KEY (`idTipo`);

--
-- Indices de la tabla `tb_transaccion`
--
ALTER TABLE `tb_transaccion`
  ADD PRIMARY KEY (`idTransaccion`),
  ADD KEY `idTipoError` (`idTipoError`),
  ADD KEY `idEstado` (`idEstado`),
  ADD KEY `idTipo` (`idTipo`),
  ADD KEY `idBanco` (`idBanco`),
  ADD KEY `idClienteSalida` (`idClienteSalida`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_clientes`
--
ALTER TABLE `tb_clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `idGenero` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_marcatarjeta`
--
ALTER TABLE `tb_marcatarjeta`
  MODIFY `idMarca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tb_paises`
--
ALTER TABLE `tb_paises`
  MODIFY `idPais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=241;

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
  MODIFY `idTipoError` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
-- AUTO_INCREMENT de la tabla `tb_tipotransaccion`
--
ALTER TABLE `tb_tipotransaccion`
  MODIFY `idTipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  ADD CONSTRAINT `tb_transaccion_ibfk_4` FOREIGN KEY (`idTipo`) REFERENCES `tb_tipotransaccion` (`idTipo`),
  ADD CONSTRAINT `tb_transaccion_ibfk_5` FOREIGN KEY (`idTipoError`) REFERENCES `tb_tipoerror` (`idTipoError`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
