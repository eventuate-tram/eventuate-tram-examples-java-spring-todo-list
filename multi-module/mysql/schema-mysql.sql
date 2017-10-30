USE eventuate;

DROP table IF EXISTS  todo;

create table todo (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title varchar(255),
  completed BOOLEAN,
  execution_order INTEGER,
  deleted BOOLEAN
);