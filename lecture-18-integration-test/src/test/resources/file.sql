CREATE TABLE `User_roles` (
            `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
            `user_role_name` varchar(255) NOT NULL,
            PRIMARY KEY (`user_id`)
            );
CREATE TABLE `Diary_users` (
            `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
            `email` varchar(255) NOT NULL,
            `user_password` varchar(255) NOT NULL,
            `first_name` varchar(255) NOT NULL,
            `last_name` varchar(255) NOT NULL,
            `balance_amount` bigint(20) NOT NULL DEFAULT '0',
            `user_role_id` int(11) DEFAULT '1',
            PRIMARY KEY (`user_id`),
            UNIQUE KEY `UNIQUE` (`email`),
            KEY `user_role_idx` (`user_role_id`)
            );
CREATE TABLE `Muscles` (
           `muscle_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
           `muscle_name` varchar(45) NOT NULL,
           PRIMARY KEY (`muscle_id`),
           UNIQUE KEY `Muscle_id_UNIQUE` (`muscle_id`),
           UNIQUE KEY `Muscle_name_UNIQUE` (`muscle_name`)
           );
CREATE TABLE `Exercises_names` (
            `name_exercises_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
            `name` varchar(255) NOT NULL,
            PRIMARY KEY (`name_exercises_id`),
            UNIQUE KEY `Unique` (`name`)
            );
CREATE TABLE `Muscles_x_Exercises_names` (
            `name_exercises_id` BIGINT(20) NOT NULL,
            `muscle_id` BIGINT(20) NOT NULL,
            UNIQUE KEY `Name_Exercises_id` (`name_exercises_id`,`muscle_id`),
            KEY `Muscles_x_Exercises_name_ibfk_1` (`muscle_id`),
            CONSTRAINT `Muscles_x_Exercises_name_ibfk_1` FOREIGN KEY (`muscle_id`) REFERENCES `Muscles` (`muscle_id`),
            CONSTRAINT `Muscles_x_Exercises_name_ibfk_2` FOREIGN KEY (`name_exercises_id`) REFERENCES `Exercises_names` (`name_exercises_id`)
            );
CREATE TABLE `Trainings` (
            `training_id` varchar(255) NOT NULL,
            `training_date` datetime DEFAULT NULL,
            `user_id` BIGINT(20) NOT NULL,
            PRIMARY KEY (`training_id`),
            KEY `user_name_idx` (`user_id`),
            CONSTRAINT `user_name` FOREIGN KEY (`user_id`) REFERENCES `Diary_users` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
            );
CREATE TABLE `Exercises_on_trainings` (
            `exercise_id` bigint(20) NOT NULL,
            `training_id` varchar(255) NOT NULL,
            `name_exercises_id` bigint(20) NOT NULL,
            `total_repeats` int(11) DEFAULT NULL,
            `total_time` time DEFAULT NULL,
            `total_weight` int(11) DEFAULT NULL,
            `total_approaches` int(20) DEFAULT NULL,
            PRIMARY KEY (`exercise_id`),
            KEY `Training_id_fk_idx` (`training_id`),
            KEY `Muscle_id_fk_idx` (`name_exercises_id`),
            CONSTRAINT `Name_Exercises_fk` FOREIGN KEY (`name_exercises_id`) REFERENCES `Exercises_names` (`name_exercises_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
            CONSTRAINT `Training_id_fk` FOREIGN KEY (`training_id`) REFERENCES `Trainings` (`training_id`) ON DELETE NO ACTION ON UPDATE CASCADE
            );
CREATE TABLE `Approaches` (
            `exercise_id` bigint(20) NOT NULL,
            `approach_id` bigint(20) NOT NULL AUTO_INCREMENT,
            `approach_counter` bigint(20) DEFAULT NULL,
            `time` time DEFAULT NULL,
            `weight` int(11) DEFAULT NULL,
            `repeats` int(11) DEFAULT NULL,
            PRIMARY KEY (`approach_id`),
            KEY `Exercise_idx` (`exercise_id`)
            );
INSERT INTO Exercises_names(name)
            VALUES ( 'Pull ups'),( 'Push ups'),( 'Bench press'),( 'Raising dumbells'),( 'Deadlift'),( 'Squats');
INSERT INTO User_roles(user_id,user_role_name)
            VALUES (2, 'Coach'),(3, 'Admin'),(1, 'Athlete');
INSERT INTO Muscles(muscle_id,muscle_name)
                VALUES (1,'Trycep'),(2,'Bycep'),(3,'Calves'),(4,'Upper back'),(5,'Abdominals'),(6,'Chest');
INSERT INTO Diary_users(user_id,email,user_password,first_name,last_name,balance_amount)
                VALUES (1,'grb@tut.by', 'Bicep','Denis', 'Samusenko',100),(2,'321@tut.by', 'www','Artuom', 'Samusenko',200),
                (3,'7891@tut.by', 'ppp','Sergei', 'Spiridonov',200),(4,'7891eddh1@tut.by', 'ddd','Sergei', 'Borisevich',200);
INSERT INTO Trainings(training_id,training_date,user_id)
            VALUES(1,'28.10.2021',1),(2,'24.10.2021',2),
            (3,'24.10.2021',3),(4,'22.10.2021',1);
INSERT INTO Exercises_on_trainings(exercise_id,training_id,name_exercises_id,total_repeats,total_time,total_weight,total_approaches)
            VALUES (1, 1, 2,200, '01:00:01',1000,5), (2, 1, 2,100, '01:00:01',1000,5),
            (3, 2, 3,20, '01:00:01',1000,5), (4, 1, 2,100, '01:00:01',1000,5), (5, 4, 4,10, '01:00:01',1000,5);
INSERT INTO Approaches(exercise_id,approach_counter,time,weight,repeats)
            VALUES (2, 1,'00:02:01', 50, 10),(2, 2,'00:02:01', 40, 12),(3, 1,'00:02:01', 60, 13),
            (1, 1,'00:02:01', 50, 10),(3, 1,'00:02:01', 80, 18);
INSERT INTO Muscles_x_Exercises_names VALUES (1,2),(1,4);