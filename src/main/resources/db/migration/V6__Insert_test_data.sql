INSERT INTO authors (name, email) VALUES
('John Doe', 'john@example.com'),
('Jane Smith', 'jane@example.com');

INSERT INTO tags (name) VALUES
('java'), ('spring'), ('database'), ('tutorial');

INSERT INTO posts (title, content, status, author_id) VALUES
('My First Post', 'Hello world!', 'PUBLISHED', 1),
('Spring Boot Tips', 'Useful tips for Spring Boot', 'PUBLISHED', 2);
