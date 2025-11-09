USE freedesign;

INSERT INTO `users` (`username`, `password`, `enabled`, `first_name`, `last_name`) VALUES ('testUser', '$2a$10$YE6yUe/ByVJCQEvAa74zA.Wbiy74RUx4CXZdUGv7k1xy1ysSZzr1W', true, 'John','Doe');
INSERT INTO `users` (`username`, `password`, `enabled`, `first_name`, `last_name`) VALUES ('testUser2', '$2a$10$C6C7klzm3zU2HWJy/m/Xf.x3BWBeckbo4fe/dSHTvwoS.8DTOfMVG', true, 'Test','User');
INSERT INTO `users` (`username`, `password`, `enabled`, `first_name`, `last_name`) VALUES ('admin', '$2a$10$YE6yUe/ByVJCQEvAa74zA.TYPjl64FhlDHX4viUNnWhwRMPZMLmje', true, 'System','Admin');

INSERT INTO artworks (name,price,notes,image_file_name,user_id,status) VALUES
('River drawing', 1500,'I want to sell it asap', '001.jpeg',1, 'APPROVED'),
('Birds in the wild', 93458, '', '002.jpeg',1, 'DRAFT'),
('Nature', 750,'not for sale now', '003.jpeg',1, 'APPROVED'),
('Temple', 500,'Temple', '004.jpeg',1, 'DRAFT'),
('Park', 900,'to save here', '005.jpeg',1, 'APPROVED'),
('Little Bird', 290,'Want to sell this photo', '006.jpeg',1, 'APPROVED'),
('Autumn', 270,'Autumn park', '007.jpeg',2, 'DRAFT'),
('Mountains', 1000,'Heaven.', '008.jpeg',2, 'APPROVED'),
('Butterflies', 1000,'Blue background butterflies', '009.jpeg',2, 'DRAFT'),
('Sad cat', 200,'Sad cat in the rain', '010.jpg',2, 'PENDING_REVIEW');

INSERT INTO `roles` (`name`) VALUES ('ROLE_USER');
INSERT INTO `roles` (`name`) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (3, 2);