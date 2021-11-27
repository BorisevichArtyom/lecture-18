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