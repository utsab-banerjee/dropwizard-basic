SQL QUERIES
############################

CREATE TABLE `merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchantName` varchar(20) NOT NULL,
  `merchantCode` varchar(20) NOT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT 1,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY `pk` (`id`),
  UNIQUE KEY `uk_merchantName_merchantCode` (`merchantName`,`merchantCode`),
  UNIQUE KEY `uk_userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `merchantAttribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchantId` int(11) NOT NULL,
  `attributeKey` varchar(45) NOT NULL,
  `attributeValue` text NOT NULL,
  `isActive` tinyint(1) DEFAULT 1,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY `pk` (`id`),
  KEY `idx_merchantId` (`merchantId`),
  UNIQUE KEY `uk_merchantId_attributeKey_isActive` (`merchantId`, `attributeKey`, `isActive`),
  CONSTRAINT `fk_merchantId` FOREIGN KEY (`merchantId`) REFERENCES `merchant` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `merchant` (`id`, `merchantName`, `merchantCode`, `userName`, `password`, `isActive`, `createdAt`, `updatedAt`)
VALUES
	(1, 'Myntra', 'MYN', 'myntra', 'hggjkhgj', 0, '2017-08-12 12:06:52', '2017-08-12 12:07:05');

INSERT INTO `merchantAttribute` (`id`, `merchantId`, `attributeKey`, `attributeValue`, `isActive`, `createdAt`, `updatedAt`)
VALUES
	(1, 1, 'gstn', 'gstn1	', 1, '2017-08-12 14:11:55', '2017-08-12 14:11:55'),
	(2, 1, 'cgstn', 'cgstn1	', 1, '2017-08-12 14:11:55', '2017-08-12 14:11:55');

##############################################