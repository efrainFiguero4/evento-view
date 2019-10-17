CREATE TABLE `evento` (
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `usuarioCreacion` int(11) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `usuarioModificacion` int(11) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`idEvento`),
  UNIQUE KEY `idEvento_UNIQUE` (`idEvento`)
) ENGINE=InnoDB AUTO_INCREMENT=0 ;


CREATE TABLE `funcion` (
  `idFuncion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `usuarioCreacion` int(11) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `usuarioModificacion` int(11) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`idFuncion`),
  UNIQUE KEY `idFuncion_UNIQUE` (`idFuncion`)
) ENGINE=InnoDB AUTO_INCREMENT=0 ;


CREATE TABLE `zona` (
  `idZona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `usuarioCreacion` int(11) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `usuarioModificacion` int(11) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`idZona`),
  UNIQUE KEY `idZona_UNIQUE` (`idZona`)
) ENGINE=InnoDB AUTO_INCREMENT=0 ;

CREATE TABLE `codigo` (
  `idCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) NOT NULL,
  `idEvento` int(11) NOT NULL,
  `idFuncion` int(11) NOT NULL,
  `idZona` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `usuarioCreacion` int(11) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `usuarioModificacion` int(11) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`idCodigo`),
  KEY `funcion_fk` (`idFuncion`),
  KEY `zona_fk` (`idZona`),
  KEY `evento_fk` (`idEvento`),
  CONSTRAINT `evento_fk` FOREIGN KEY (`idEvento`) REFERENCES `evento` (`idEvento`),
  CONSTRAINT `funcion_fk` FOREIGN KEY (`idFuncion`) REFERENCES `funcion` (`idFuncion`),
  CONSTRAINT `zona_fk` FOREIGN KEY (`idZona`) REFERENCES `zona` (`idZona`)
) ENGINE=InnoDB AUTO_INCREMENT=0 ;


CREATE TABLE `Usuario` (
  `usuarioId` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL,
  `contrasenia` varchar(50) NOT NULL,
  `nombreUsuario` varchar(255) NOT NULL,
  `estado` bit(1) NOT NULL,
  `usuarioCreacion` int(11) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `usuarioModificacion` int(11) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`usuarioId`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=0 ;


INSERT INTO Usuario (usuario,contrasenia, nombreUsuario, estado, usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion) VALUES ("admin", "admin", "admin", 1, 1, now(), 1, now());

INSERT INTO Usuario (usuario,contrasenia, nombreUsuario, estado, usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion ) VALUES ("usuario", "usuario", "usuario", 1, 1, now(), 1, now());

CREATE TABLE `configuracion` (
  `idConfiguracion` int(11) NOT NULL AUTO_INCREMENT,
  `idEvento` int(11) NOT NULL,
  `idFuncion` int(11) NOT NULL,
  `idZona` int(11) NOT NULL,
  PRIMARY KEY (`idConfiguracion`)
) ENGINE=InnoDB AUTO_INCREMENT=0 ;


INSERT INTO configuracion (idEvento, idFuncion, idZona) VALUES (1, 1, 1);
