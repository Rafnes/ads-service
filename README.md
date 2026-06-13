# Ads-Service Marketplace Service

Данный проект представляет собой бэкенд-приложение для платформы объявлений с функциональностью авторизации, аутентификации пользователей, управления объявлениями и комментариями, а также загрузки и отображения изображений. Проект реализует трехслойную архитектуру, использующую DTO, контроллеры, репозитории, сервисы и мапперы. Все данные хранятся в базе данных PostgreSQL.

## Содержание

- [Технологии](#технологии)
- [Основные функции](#основные-функции)
- [Структура проекта](#структура-проекта)
- [Начало работы](#начало-работы)
- [Настройка проекта](#настройка-проекта)
- [API](#API)
- [Роли и доступ](#роли-и-доступ)
- [Тестирование](#тестирование)
- [Команда проекта](#команда-проекта)

## Технологии

- [Java](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL](https://www.postgresql.org/)
- [Swagger](https://swagger.io/)
- [Swagger Editor](https://editor.swagger.io/)
- [Postman](https://www.postman.com/)
- [Lombok](https://projectlombok.org/)
- [Maven](https://maven.apache.org/)
- [Liquibase](https://www.liquibase.com/)
- [JUnit](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
- [Git](https://git-scm.com/)

## Основные функции

• Авторизация и аутентификация пользователей с распределением ролей (пользователь и администратор).

• CRUD-операции для работы с объявлениями и комментариями:
  • Администратор может удалять или редактировать все объявления и комментарии.
  • Пользователь может редактировать или удалять только свои объявления и комментарии.

• Комментарии под каждым объявлением, с возможностью их добавления пользователями.

• Загрузка и отображение изображений:
  • Картинки для объявлений.
  • Аватарки для пользователей.

## Структура проекта

Проект состоит из нескольких основных слоев:

• DTO — Объекты для передачи данных между слоями (Data Transfer Objects).
• Controllers — Обрабатывают HTTP-запросы и взаимодействуют с сервисами.
• Entities — Представляют данные в базе данных (например User, Ad, Comment).
• Repositories — Интерфейсы для работы с базой данных с использованием Spring Data JPA.
• Services — Логика работы с сущностями и преобразование данных из/в DTO.
• Mappers — Утилиты для преобразования сущностей в DTO и обратно.

## Начало работы

### Требования

Для установки и запуска проекта, необходимы:

- Java 17 (https://www.oracle.com/java/)
- Maven (https://maven.apache.org/)
- Spring Boot 2.5+ (https://start.spring.io/)
- PostgreSQL (https://www.postgresql.org/)
- Spring Security (https://spring.io/projects/spring-security)
- JUnit (https://junit.org/junit5/)
- Docker (https://www.docker.com/)

## Настройка проекта

1. Клонировать репозиторий:

   ```git clone <url>```
  ```cd <project-folder>```

2. Установить зависимости с помощью Maven:

      ```mvn clean install```
   
3. Установите и настройте PostgreSQL локально. Создайте базу данных и пользователя.
Вы можете скачать и установить PostgreSQL с официального сайта: PostgreSQL Downloads (https://www.postgresql.org/download/).

4. Настройте подключение к базе данных в файле application.properties:

```
spring.application.name=ads-service
build.version=1.0.0
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.liquibase.change-log=classpath:db/changelog-master.yml
spring.liquibase.default-schema=public
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

5. Запуск приложения:

      ```mvn spring-boot:run```
   

Проект будет доступен по адресу:

http://localhost:8080

## API

Проект предоставляет RESTful API с основными методами для работы с объявлениями, комментариями и изображениями. Пример запросов:

### Авторизация:

• POST /login — Вход в систему. Параметры: username, password.

### Регистрация:

• POST /register — Регистрация нового пользователя. Параметры: username, password, role, firstName, lastName, phone.

### Пользователи: 

• POST /users/set_password — Сменить пароль

• GET /users/me — Получить информацию о пользователе

• PATCH /users/me — Обновить данные пользователя

• PATCH /users/me/image — Обновить аватар пользователя

• GET /users/me/image/{id}/get — Получить изображение пользователя

### Объявления:

• GET /ads — Получение списка всех объявлений

• POST /ads — Создание нового объявления

• GET /ads/{id} — Получение информации об объявлении

• DELETE /ads/{id} — Удаление объявления

• PATCH /ads/{id} — Редактирование объявления

• GET /ads/me — Получение объявлений авторизованного пользователя

• PATCH /ads/{id}/image — Обновление картинки объявления

### Комментарии:

• GET /ads/{Adid}/comments — Получение комментариев для объявления

• POST /ads/{Adid}/comments — Добавление комментария под объявлением

• PATCH /ads/{adId}/comments/{commentId} — Редактирование комментария

• DELETE /ads/{adId}/comments/{commentId} — Удаление комментария

### Роли и доступ

Пользователь:

• Может создавать, редактировать и удалять только свои объявления и комментарии.
• Может добавлять комментарии под объявлениями.
• Может изменять свою аватарку и просматривать изображения.

Администратор:

• Может управлять всеми объявлениями и комментариями.
• Может изменять аватарки пользователей.

## Тестирование

Проект включает интеграционные тесты для всех основных сервисов. Для запуска тестов используйте команду:
```mvn test```

Тесты проверяют основные функциональности:

- Регистрация и авторизация пользователей.

- Управление объявлениями и комментариями.

- Обработка изображений.

## Команда проекта

Vitaly Dineka — Main Developer

Irina Bogomolova — PM

Ivan Pesterev — Developer

Andrei Fetisov —  QA
