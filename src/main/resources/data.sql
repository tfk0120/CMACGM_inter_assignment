DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  phone VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL,
  zip_code VARCHAR(20) NOT NULL,
  addr_line VARCHAR(50) NOT NULL,
  addr_supp VARCHAR(250)  NULL,
  career VARCHAR(250) NULL
);
INSERT INTO customer (first_name, last_name, phone, email, city, zip_code, addr_line, addr_supp, career) VALUES 
('first_name', 'last_name', 'phone', 'email', 'city', 'zip_code', 'addr_line', 'addr_supp', 'career');

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

INSERT INTO user (user_name, password) VALUES ('user-test','$2a$10$bKBnD7BGyXKZYjuky7uL6.19Em7DQeo4fLftoAkaLFS25gLAmq4hG');


commit;