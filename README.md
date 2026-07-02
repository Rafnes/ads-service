# Ads-Service Marketplace Service

Бэкенд-приложение для платформы объявлений с функциональностью авторизации, аутентификации пользователей, управления объявлениями и комментариями, а также загрузки и отображения изображений. Проект реализует трехслойную архитектуру (Контроллеры -> Сервисы -> Репозитории) с использованием DTO и мапперов для разлеления слоев данных. Все данные хранятся в базе данных PostgreSQL.

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

## Основные функции и роли

В системе настроено ролевое разграничение доступа:

• **Пользователь (USER):**
  * Может создавать объявления и оставлять комментарии.
  * Имеет право редактировать и удалять только **свои собственные** объявления и комментарии.
  * Может загружать и обновлять свой аватар и фотографии к своим объявлениям.
• **Администратор (ADMIN):**
  * Обладает всеми правами пользователя.
  * Может модерировать сервис: редактировать или **удалять любые** объявления и комментарии в системе.

## Структура проекта

• DTO — Объекты для передачи данных между клиентом и сервером (Data Transfer Objects).
• Controllers — REST-контроллеры, обрабатывающие входящие HTTP-запросы.
• Entities — Доменные модели/сущности базы данных (User, Ad, Comment).
• Repositories — Интерфейсы доступа к данным, расширяющие JpaRepository.
• Services — Слой бизнес-логики приложения.
• Mappers — Классы для маппинга и преобразования сущностей в DTO и обратно.

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
   
3. Установите и настройте PostgreSQL локально. Создайте базу данных (через pgAdmin или psql) и пользователя.

      ```CREATE DATABASE ads_db;```
   
5. Настройте подключение к базе данных в файле application.properties:

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
   

Приложение запустится локально на порту 8080: http://localhost:8080

## API

Проект предоставляет RESTful API с основными методами для работы с объявлениями, комментариями и изображениями. Пример запросов:

### Авторизация и регистрация:

• POST /login — Вход в систему. Параметры: username, password.

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


## Тестирование

Проект покрыт интеграционными тестами, которые проверяют цепочки регистрации, авторизации, CRUD-операции над объявлениями и корректность разграничения прав доступа.

Для запуска тестов выполните:
```mvn test```


## Команда проекта

Vitaly Dineka — Main Developer

Irina Bogomolova — PM

Ivan Pesterev — Developer

Andrei Fetisov — QA

Andrei Fetisov —  QA
