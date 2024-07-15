CREATE TABLE courses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  category VARCHAR(50) NOT NULL
);

CREATE TABLE topics (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  message TEXT NOT NULL,
  status VARCHAR(50) NOT NULL,
  course_id BIGINT NOT NULL,
  author_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (course_id) REFERENCES courses(id),
  FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE TABLE answers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  message TEXT NOT NULL,
  solution TEXT NOT NULL,
  topic_id BIGINT NOT NULL,
  author_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (topic_id) REFERENCES topics(id),
  FOREIGN KEY (author_id) REFERENCES users(id)
);
