# HOST: localhost (Version )
# Date: 2024.03.05 00:19:00
# Generator: MySQL

#
# Structure for table "users"
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `usersUuid` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `emp_no` varchar(255) NOT NULL,
                         `role` char(1) NOT NULL,
                         `phone_no` varchar(255) NOT NULL,
                         `address_no` varchar(255) NOT NULL,
                         `profile` varchar(255) NOT NULL,
                         `dept_code` char(20) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "users"
#

-- INSERT INTO `users` VALUES (1, 'rwkang', )

#
# Structure for table "users"
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `usersUuid` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `emp_no` varchar(255) NOT NULL,
                         `role` char(1) NOT NULL,
                         `phone_no` varchar(255) NOT NULL,
                         `address_no` varchar(255) NOT NULL,
                         `profile` varchar(255) NOT NULL,
                         `dept_code` char(20) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "member"
#

-- INSERT INTO `member` VALUES (1, 'rwkang', )

#
# Structure for table "member"
#

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `usersUuid` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `emp_no` varchar(255) NOT NULL,
                         `role` char(1) NOT NULL,
                         `phone_no` varchar(255) NOT NULL,
                         `address_no` varchar(255) NOT NULL,
                         `profile` varchar(255) NOT NULL,
                         `dept_code` char(20) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "menu"
#

-- INSERT INTO `menu` VALUES (1, 'rwkang', )

#
# Structure for table "menu"
#

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `code` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `url` varchar(255) NOT NULL,
                         `roles` varchar(255) NOT NULL,
                         `detailed` varchar(255) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "post"
#

-- INSERT INTO `post` VALUES (1, 'rwkang', )

#
# Structure for table "post"
#

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `post_id` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `title` varchar(255) NOT NULL,
                         `contents` varchar(255) NOT NULL,
                         `author` varchar(255) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "score"
#

-- INSERT INTO `score` VALUES (1, 'rwkang', )

#
# Structure for table "score"
#

DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `subject` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `score` INT NOT NULL,
                         `student_id` int(11) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "student"
#

-- INSERT INTO `student` VALUES (1, 'rwkang', )

#
# Structure for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) NOT NULL,
                         `age` int(11) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "weather"
#

-- INSERT INTO `weather` VALUES (1, 'rwkang', )


#
# Structure for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `weather` varchar(255) NOT NULL,
                           `icon` varchar(255) NOT NULL,
                           `temperature` decimal(18.4) NOT NULL,
                           `text` varchar(255) NOT NULL,
                           `created_at` DATETIME NOT NULL DEFAULT CURRENT_DATETIME,
                           `updated_at` DATETIME,
                           PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#
# Data for table "weather"
#

-- INSERT INTO `weather` VALUES (1, 'rwkang', )

#
# Structure for table "dept"
#

DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `dept_code` varchar(255) NOT NULL,
                         `dept_name` varchar(255) NOT NULL,
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

