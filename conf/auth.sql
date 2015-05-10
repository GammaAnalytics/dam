CREATE DATABASE damConsole;

USE damConsole;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(1,	'Application Administrator',	'ROLE_ADMIN'),
(2,	'Super User Has All Rights Except User Modification',	'ROLE_SUPER'),
(3,	'Application Administrator',	'ROLE_USER');

DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `role_name` varchar(20) NOT NULL,
  `permission` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `roles_permissions` (`role_name`, `permission`) VALUES
('ROLE_ADMIN',	'admin:*'),
('ROLE_SUPER',	'admin:r'),
('ROLE_USER',	'user:rw');

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `username` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(100) DEFAULT NULL,
  `valid` date DEFAULT NULL,
  `activated` tinyint(1) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `users` (`id`, `first_name`, `last_name`, `username`, `password`, `valid`, `activated`, `email`) VALUES
(1401112525121,	'Administrator',	NULL,	'admin',	'VvxYo/U2Udw=',	'2014-12-31',	1,	'abhijit@gammanalytics.com');

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `username` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `user_role` (`user_id`, `role_id`, `username`) VALUES
(1401112525121,	1,	'admin');