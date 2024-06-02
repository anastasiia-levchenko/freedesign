USE freedesign;
insert into artworks (name,price,want_to_sell,notes,image_file_name) values
('River drawing', 1500, true,'I want to sell it asap', '001.jpeg'),
('Birds in the wild', 93458, false, '', '002.jpeg'),
('Nature', 750, false,'not for sale now', '003.jpeg'),
('Temple', 500,true,'Temple', '004.jpeg'),
('Park', 900, false,'to save here', '005.jpeg'),
('Little Bird', 290,true,'Want to sell this photo', '006.jpeg'),
('Autumn', 270,true,'Autumn park', '007.jpeg'),
('Mountains', 1000, true,'Heaven.', '008.jpeg'),
('Butterflies', 1000, true,'Blue background butterflies', '009.jpeg'),
('Sad cat', 200, false,'Sad cat in the rain', '010.jpg');


INSERT INTO `users` (`username`, `password`, `enabled`, `first_name`, `last_name`) VALUES ('testUser', '$2a$10$YE6yUe/ByVJCQEvAa74zA.Wbiy74RUx4CXZdUGv7k1xy1ysSZzr1W', true, 'John','Doe');
INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);