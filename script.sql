CREATE TABLE `customers2` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `otchestvo` varchar(255) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `passport_series` varchar(255) DEFAULT NULL,
  `passport_number` varchar(255) DEFAULT NULL,
  `home_town` enum('Minsk','Zhodino','Moscow','Mogilev','Grodno') DEFAULT NULL,
  `fact_adress` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `work_status` bit(1) DEFAULT NULL,
  `hierarchy_status` varchar(255) DEFAULT NULL,
  `propiska_adress` varchar(255) DEFAULT NULL,
  `citizenship` enum('Belarus','Russia','France','USA','China') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
