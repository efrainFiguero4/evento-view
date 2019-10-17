CREATE TABLE `configuracion` (
  `idConfiguracion` int(11) NOT NULL AUTO_INCREMENT,
  `idEvento` int(11) NOT NULL,
  `idFuncion` int(11) NOT NULL,
  `idZona` int(11) NOT NULL,
  PRIMARY KEY (`idConfiguracion`),
  KEY `funcion_conf_fk` (`idFuncion`),
  KEY `zona_conf_fk` (`idZona`),
  KEY `evento_conf_fk` (`idEvento`),
  CONSTRAINT `evento_conf_fk` FOREIGN KEY (`idEvento`) REFERENCES `evento` (`idEvento`),
  CONSTRAINT `funcion_conf_fk` FOREIGN KEY (`idFuncion`) REFERENCES `funcion` (`idFuncion`),
  CONSTRAINT `zona_conf_fk` FOREIGN KEY (`idZona`) REFERENCES `zona` (`idZona`)
) ENGINE=InnoDB AUTO_INCREMENT=0 ;
