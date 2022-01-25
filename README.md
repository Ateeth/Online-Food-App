# Online-Food-App
SQL Database Commands
for database creation
i) create the foods table to store all foods
   CREATE TABLE 'foods' (
  'id' int NOT NULL,
  'name' varchar(250) NOT NULL,
  'price' double NOT NULL,
  'category' varchar(45) NOT NULL,
  'image' varchar(250) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

ii) create the starters table to store all starters
CREATE TABLE 'starters' (
  'id' int NOT NULL AUTO_INCREMENT,
  'name' varchar(250) NOT NULL,
  'price' double NOT NULL,
  'category' varchar(45) NOT NULL,
  'image' varchar(250) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

iii) create the desserts table to store all desserts
CREATE TABLE 'desserts' (
  'id' int NOT NULL AUTO_INCREMENT,
  'name' varchar(250) NOT NULL,
  'price' double NOT NULL,
  'category' varchar(45) NOT NULL,
  'image' varchar(250) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

iv) create the maincoursedishes table to store all main course dishes
CREATE TABLE 'maincoursedishes' (
  'id' int NOT NULL AUTO_INCREMENT,
  'name' varchar(250) NOT NULL,
  'price' double NOT NULL,
  'category' varchar(45) NOT NULL,
  'image' varchar(250) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

v) create the orders table to store details of all placed orders
CREATE TABLE 'orders' (
  'o_id' int NOT NULL AUTO_INCREMENT,
  'p_id' int NOT NULL,
  'u_id' int NOT NULL,
  'o_quantity' int NOT NULL,
  'o_date' varchar(450) NOT NULL,
  PRIMARY KEY ('o_id')
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

vi) create the users table to store details of all users and their passwords
CREATE TABLE 'users' (
  'id' int NOT NULL AUTO_INCREMENT,
  'name' varchar(250) NOT NULL,
  'email' varchar(250) NOT NULL,
  'password' varchar(250) NOT NULL,
  PRIMARY KEY ('id'),
  UNIQUE KEY 'email_UNIQUE' ('email')
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

NOTE : 
New users will be added to the users table using a servlet when a new user fills the registration form
New orders will be added to the orders table when a new order is placed using buy now function. This is handled by a servlet in the application

New foods must first be added to the foods table then also the exact same values can be added to starters/maincoursedishes/desserts table which ever category the added food comes under
Also the id value of the new food item when being added to starters/desserts/maincoursedishes table must be same as its id in the foods table.
The reason this is to be followed is because the food may be added to cart either from home page/starters page/maincoursedishes page/desserts page their id must be the same in the cart

Simple sql create statements can be used to add food items in foods/starters/desserts/maincoursedishes table

And also when the image name of a new food is entered in the database the corresponding image must be inserted into the food-images folder of the webapp in the directory.

Also in DbCon.java you have to edit the Connection statement with DriverManager i.e database name , password 
To use the webapp the following are prerequisites
i) an ide
ii) Apache Tomcat Server
iii) an sql connection with MySQL workbench
iii) conversion of the web application to a maven project as all dependinces are installed and mentioned in the pom.xml file of the application
iv) and then run the application on the server (from whatever file the application is run for authentication purposes the application shows only the login/register page first)
v) if you are a new user the registration option will be able to be accessed via the navbar and when an account is created the user may then login to the database
