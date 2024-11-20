# eatory_db

DROP DATABASE IF EXISTS eatory_db;
CREATE DATABASE eatory_db DEFAULT CHARACTER SET utf8mb4;

USE eatory_db;

CREATE TABLE `User` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `height` BIGINT NULL,
    `weight` BIGINT NULL,
    `gender` CHAR(1) NULL,
    `birth_date` DATE NULL,
    `profile_image` VARCHAR(255) NULL,
    `phone_number` VARCHAR(255) NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `UserAllergy` (
    `user_id` BIGINT NOT NULL,
    `allergy_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `allergy_id`),
    FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`),
    FOREIGN KEY (`allergy_id`) REFERENCES `Allergy`(`allergy_id`)
);

CREATE TABLE Allergy (
    allergy_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    allergy_name VARCHAR(255) NOT NULL
);

CREATE TABLE Post (
    post_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    post_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    image_url TEXT NULL,
    content TEXT NULL
);

CREATE TABLE Follow (
    follower_id BIGINT NOT NULL,
    followee_id BIGINT NOT NULL,
    follow_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, followee_id),
    FOREIGN KEY (follower_id) REFERENCES User(user_id),
    FOREIGN KEY (followee_id) REFERENCES User(user_id)
);


INSERT INTO `User` (username, password, email, height, weight, gender, birth_date, profile_image, phone_number)
VALUES
('user1', 'pass1', 'user1@example.com', 170, 65, 'M', '1990-01-01', 'image1.jpg', '010-1234-5678'),
('user2', 'pass2', 'user2@example.com', 160, 55, 'F', '1992-02-02', 'image2.jpg', '010-2345-6789'),
('user3', 'pass3', 'user3@example.com', 175, 70, 'M', '1988-03-03', 'image3.jpg', '010-3456-7890'),
('user4', 'pass4', 'user4@example.com', 165, 50, 'F', '1995-04-04', 'image4.jpg', '010-4567-8901'),
('user5', 'pass5', 'user5@example.com', 180, 80, 'M', '1985-05-05', 'image5.jpg', '010-5678-9012'),
('user6', 'pass6', 'user6@example.com', 155, 48, 'F', '1993-06-06', 'image6.jpg', '010-6789-0123'),
('user7', 'pass7', 'user7@example.com', 172, 68, 'M', '1991-07-07', 'image7.jpg', '010-7890-1234'),
('user8', 'pass8', 'user8@example.com', 168, 58, 'F', '1994-08-08', 'image8.jpg', '010-8901-2345'),
('user9', 'pass9', 'user9@example.com', 185, 75, 'M', '1989-09-09', 'image9.jpg', '010-9012-3456'),
('user10', 'pass10', 'user10@example.com', 162, 52, 'F', '1996-10-10', 'image10.jpg', '010-0123-4567');

INSERT INTO Allergy (allergy_name)
VALUES
('Peanuts'),
('Shellfish'),
('Milk'),
('Gluten'),
('Eggs'),
('Fish');

INSERT INTO UserAllergy (user_id, allergy_id)
VALUES
(1, 1), -- user1 has Peanuts allergy
(1, 3), -- user1 also has Milk allergy
(2, 2), -- user2 has Shellfish allergy
(3, 5), -- user3 has Eggs allergy
(3, 6), -- user3 also has Fish allergy
(4, 4), -- user4 has Gluten allergy
(5, 1), -- user5 has Peanuts allergy
(6, 6), -- user6 has Fish allergy
(7, 2), -- user7 has Shellfish allergy
(8, 3), -- user8 has Milk allergy
(9, 4), -- user9 has Gluten allergy
(10, 5); -- user10 has Eggs allergy

INSERT INTO Post (user_id, post_time, image_url, content)
VALUES
(1, '2023-11-10 10:00:00', 'http://example.com/image1.jpg', 'Enjoyed a delicious breakfast!'),
(2, '2023-11-11 12:30:00', 'http://example.com/image2.jpg', 'Lunch with friends was amazing!'),
(3, '2023-11-12 14:00:00', 'http://example.com/image3.jpg', 'Tried a new dinner recipe.'),
(4, '2023-11-13 18:00:00', 'http://example.com/image4.jpg', 'Exploring new healthy snacks.'),
(5, '2023-11-14 20:00:00', 'http://example.com/image5.jpg', 'Fitness meal for the evening.'),
(6, '2023-11-15 07:30:00', 'http://example.com/image6.jpg', 'Quick breakfast before work.'),
(7, '2023-11-16 09:00:00', 'http://example.com/image7.jpg', 'Healthy brunch to start the day.'),
(8, '2023-11-17 19:00:00', 'http://example.com/image8.jpg', 'Dinner with family was fantastic!'),
(9, '2023-11-18 08:00:00', 'http://example.com/image9.jpg', 'Early morning jog and breakfast.'),
(10, '2023-11-19 21:00:00', 'http://example.com/image10.jpg', 'Post-workout meal to recharge.');


INSERT INTO Follow (follower_id, followee_id, follow_date)
VALUES
(1, 2, '2023-11-10 10:00:00'), -- user1 follows user2
(2, 3, '2023-11-11 12:00:00'), -- user2 follows user3
(3, 4, '2023-11-12 14:00:00'), -- user3 follows user4
(4, 5, '2023-11-13 16:00:00'), -- user4 follows user5
(5, 6, '2023-11-14 18:00:00'), -- user5 follows user6
(6, 7, '2023-11-15 08:00:00'), -- user6 follows user7
(7, 8, '2023-11-16 09:30:00'), -- user7 follows user8
(8, 9, '2023-11-17 10:00:00'), -- user8 follows user9
(9, 10, '2023-11-18 11:00:00'), -- user9 follows user10
(10, 1, '2023-11-19 12:00:00'); -- user10 follows user1




