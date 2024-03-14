# Тестовое задание на стажировку VK
## Суть задания
Организовать сервис для обращения к [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
Разработать:
- GET, POST, PUT, DELETE-запросы
- сделать авторизацию/регистрацию с доступами (специальными/полными - ADMIN)
## Структура проекта
### Запуск проекта
Проект реализован на Spring Boot с помощью Maven.
Для отправки запросов нужно перейти на http://localhost:8080 - на главной странице ссылки на Users/Posts/Albums Controller
Для доступа необходимо авторизоваться. Программа сама переведет на страницу /login.
УЗ для входа:
- уз: admin пароль: admin роль: ROLE_ADMIN
- уз: users пароль: users роль: ROLE_USERS
- уз: posts пароль: posts роль: ROLE_POSTS
- уз: albums пароль: albums роль: ROLE_ALBUMS
По адресу /registration можно зарегистрировать, надо придумать логин/пароль и ввести роль.
Все УЗ хранятся в БД в таблице USERS.
### Роли
- ROLE_ADMIN - доступMVC ко всем контроллерам
- ROLE_USERS - доступ к /api/users
- ROLE_POSTS - доступ к /api/posts
- ROLE_ALBUMS - доступ к /api/albums
### БД
БД реализована на H2 (для простоты запуска)
Используется 2 таблицы: USERS, LOGS.
#### USERS
![image](https://github.com/bmsalikhov/vktestapp/assets/153372291/27b8c97c-29ea-4833-b94b-2558f8c3e2de)
Таблица для хранения юзеров
Храним логин, пароль, айди и роль каждой УЗ
#### LOGS
![image](https://github.com/bmsalikhov/vktestapp/assets/153372291/df6141a5-41ed-4529-a567-ab6d9343e8a6)
Таблица для хранения логов - записываем каждый запрос, его успешность/неуспешность, дату и адрес запроса. Все логи выводятся на главной странице по адрес /
### Безопасность
Безопасность реализована с помощью Spring Security. Все пароли кодируются с помощью BCrypt
важно: отключен csrf, потому что он блокирует PUT, POST, DELETE запросы
### Зависимости
- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.2.3)
- [Spring Boot Starter Thymeleaf](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf/3.2.3)
- [Spring Boot Starter Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation/3.2.3)
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Thymeleaf Extras Springsecurity6](https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity6/3.1.2.RELEASE)
- [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools/3.2.3)
- [H2 Database Engine](https://mvnrepository.com/artifact/com.h2database/h2/2.2.224)
- [Project Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30)
- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test/3.2.3)
- [Spring Security Test](https://mvnrepository.com/artifact/org.springframework.security/spring-security-test/6.2.2)
Также использовал bootstrap и angular для вз-я фронта с бэком
### Пакеты
- #### config
Здесь находятся классы WebSecurityCondfig (безопасность), WebMvcConfig (конфигурация MVC) и CustomUserDetails
- #### controllers
Контроллеры для вз-я с API + контроллеры страниц регистрации и главной
- #### models
dto-шки для отправки POST, PUT запросов + модели ROLE, USER, LOG - для работы с УЗ и логирования
- #### repos
Репозитории для учеток и логов
- #### services
Сервисы для связи контроллеров с БД и сторонним API
- HTML-шаблоны хранятся в /resources/templates
- БД хранится в папке проекта database.mv.db
