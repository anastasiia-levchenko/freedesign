USE freedesign;

INSERT INTO `users` (`username`, `password`, `enabled`, `first_name`, `last_name`) VALUES ('testUser', '$2a$10$YE6yUe/ByVJCQEvAa74zA.Wbiy74RUx4CXZdUGv7k1xy1ysSZzr1W', true, 'John','Doe');
INSERT INTO `users` (`username`, `password`, `enabled`, `first_name`, `last_name`) VALUES ('testUser2', '$2a$10$C6C7klzm3zU2HWJy/m/Xf.x3BWBeckbo4fe/dSHTvwoS.8DTOfMVG', true, 'Test','User');
INSERT INTO `users` (`username`, `password`, `enabled`, `first_name`, `last_name`) VALUES ('admin', '$2a$10$YE6yUe/ByVJCQEvAa74zA.TYPjl64FhlDHX4viUNnWhwRMPZMLmje', true, 'System','Admin');

INSERT INTO artworks (name,price,want_to_sell,notes,image_file_name,user_id) VALUES
('River drawing', 1500, true,'I want to sell it asap', '001.jpeg',1),
('Birds in the wild', 93458, false, '', '002.jpeg',1),
('Nature', 750, false,'not for sale now', '003.jpeg',1),
('Temple', 500,true,'Temple', '004.jpeg',1),
('Park', 900, false,'to save here', '005.jpeg',1),
('Little Bird', 290,true,'Want to sell this photo', '006.jpeg',1),
('Autumn', 270,true,'Autumn park', '007.jpeg',2),
('Mountains', 1000, true,'Heaven.', '008.jpeg',2),
('Butterflies', 1000, true,'Blue background butterflies', '009.jpeg',2),
('Sad cat', 200, false,'Sad cat in the rain', '010.jpg',2);

INSERT INTO `roles` (`name`) VALUES ('ROLE_USER');
INSERT INTO `roles` (`name`) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (3, 2);