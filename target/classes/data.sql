DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL
);

INSERT INTO billionaires (user_name, password, role) VALUES
  ('pepa', 'zdepa', 'ADMIN'),
  ('josef', 'kokos', 'AGENT');