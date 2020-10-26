CREATE DATABASE twitter_clone;

-- creates user table
CREATE TABLE `en_user`( 
`id` BIGINT(20) AUTO_INCREMENT,
`name` VARCHAR(256) NOT NULL,
`age` BIGINT NOT NULL,
`password` VARCHAR(32) NOT NULL,
`total_tweets` BIGINT DEFAULT 0 NOT NULL,
PRIMARY KEY (`id`));

-- creates follower table(people whom a given user follows)
CREATE TABLE `en_followers` (
`id` BIGINT(20) AUTO_INCREMENT,
`user_id` BIGINT(20) NOT NULL,
`follower_id` BIGINT(20) NOT NULL,
PRIMARY KEY(`id`),
CONSTRAINT `f_key` FOREIGN KEY (`user_id`) REFERENCES `en_user` (`id`),
CONSTRAINT `f_key_follower` FOREIGN KEY (`follower_id`) REFERENCES `en_user` (`id`)
);

-- creates tweets table 
CREATE TABLE `en_tweets` (
`id` BIGINT(20) AUTO_INCREMENT,
`content` VARCHAR(256) NOT NULL,
`total_likes` BIGINT(20) NOT NULL,
`user_id` BIGINT(20) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `f_key_uid` FOREIGN KEY (`user_id`) REFERENCES `en_user` (`id`)
);
