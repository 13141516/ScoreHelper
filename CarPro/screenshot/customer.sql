CREATE TABLE `customer` (
  `user_name` varchar(30) NOT NULL,
  `user_password` tinytext NOT NULL,
  `user_mail` tinytext NOT NULL,
  `user_numbers` int(11) DEFAULT '0',
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
