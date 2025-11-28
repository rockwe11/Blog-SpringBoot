# Blog Platform

Spring Boot приложение для управления блогом с постами, авторами, комментариями и тегами.

## Инструкция по запуску
### 1. Клонирование и сборка
```bash
git clone https://github.com/rockwe11/Blog-SpringBoot
cd blog
mvn clean compile
```

### 2. Запуск
```bash
mvn spring-boot:run
```
Приложение будет доступно по адресу: http://localhost:8080

## H2 Console (для просмотра БД)

URL: http://localhost:8080/h2
JDBC URL: jdbc:h2:mem:blogdb
Username: sa
Password: (пусто)

## API

### Посты
```bash
GET /posts - получить все посты
POST /posts - создать новый пост
PUT /posts/{id} - обновить пост
```

### Комментарии
```bash
GET /comments - получить все комментарии
POST /comments - создать комментарий
PUT /comments/{id} - обновить комментарий
```

### Авторы
```bash
GET /authors - получить всех авторов
POST /authors - создать автора
GET /authors/{id} - получить автора по ID
PUT /authors/{id} - обновить автора
DELETE /authors/{id} - удалить автора
```

### Теги
```bash
GET /tags - получить все теги
POST /tags - создать тег
PUT /tags/{id} - обновить тег
```

## Примеры для тестирования

```bash
# Создать автора
curl -X POST http://localhost:8080/authors \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe", "email": "john@example.com"}'

# Создать теги
curl -X POST http://localhost:8080/tags \
  -H "Content-Type: application/json" \
  -d '{"name": "java"}'

curl -X POST http://localhost:8080/tags \
  -H "Content-Type: application/json" \
  -d '{"name": "spring"}'

# Создать пост
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My First Post",
    "content": "This is the content of my first blog post",
    "status": "PUBLISHED",
    "authorId": 1,
    "tags": [1, 2]
  }'

# Создать комментарий
curl -X POST http://localhost:8080/comments \
  -H "Content-Type: application/json" \
  -d '{
    "content": "Great post!",
    "authorId": 1,
    "postId": 1
  }'
```

## Технологии

- **Java 17**
- **Spring Boot 3.3.3**
- **Spring Data JPA/Hibernate**
- **H2 Database** (in-memory)
- **Flyway** (миграции базы данных)
- **Lombok**
- **Maven**