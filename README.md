Тестовое задание для стажировки в компании Nauka - приложение на Spring Boot. Оно использует Spring Data JPA для взаимодействия с базой данных, PostgreSQL в качестве базы данных, Lombok для упрощения кода и Flyway для миграций базы данных.

### Компоненты:

• Spring Boot: Фреймворк, который упрощает создание веб-приложений

• Spring Data JPA: Простой и мощный способ взаимодействия с базами данных

• PostgreSQL: Популярная реляционная база данных

• Lombok: Библиотека, которая помогает сократить объем кода

• Flyway: Инструмент для миграций базы данных

### Структура проекта:

• src/main/java: Содержит основную логику приложения, включая сущности, репозитории, сервисы и т.д.

• src/test/java: Содержит тесты

• pom.xml: Файл Maven, который определяет зависимости, настройки сборки и другую информацию о проекте

### Зависимости:

• spring-boot-starter-data-jpa: Обеспечивает поддержку Spring Data JPA

• postgresql: JDBC-драйвер для подключения к PostgreSQL

• lombok: Добавляет аннотации для упрощения кода (например, для автоматического создания геттеров, сеттеров и конструкторов)

• spring-boot-starter-test: Обеспечивает зависимости и инструменты для тестирования

• flyway-core: Ядро Flyway для управления миграциями

• flyway-database-postgresql: Обеспечивает поддержку PostgreSQL для Flyway

### Перед запуском:

• Установите PostgreSQL: Запустите PostgreSQL на вашем компьютере

• Настройте подключение к базе данных - измените настройки подключения к PostgreSQL в файле application.properties в папке src/main/resources:

   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   
   spring.datasource.username=your_username
   
   spring.datasource.password=your_password
   
Замените your_database_name, your_username и your_password на соответствующие значения.
   
### Тестирование:

Приложение поддерживает различные профили тестирования:

• first-task: Запускает тесты только для задания 2.1

• second-task: Запускает тесты, только для задания 2.2

• third-task: Запускает тесты, только для задания 2.3

• all-tasks: Запускает все тесты.

Чтобы запустить тесты с определенным профилем, используйте команду:
mvn test -P MAVEN_PROFILE 

Например:
mvn test -P first-task

Без указания профиля будут запущены тесты из профиля all-tasks.
