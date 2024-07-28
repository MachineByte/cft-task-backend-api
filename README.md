# Тестовое задание ШИФТ ЦФТ

## Описание
Проект представляет собой веб-приложение на языке Java, предназначенное для обработки интервалов чисел или букв. Основные функции приложения включают:

- Объединение пересекающихся интервалов.
- Сохранение непересекающихся интервалов в памяти SQL-базы данных (H2).
- Предоставление минимального интервала из базы данных по запросу.
- 
## Конфигурация и запуск (Windows)
1. Клонирование репозитория

```git clone https://github.com/MachineByte/cft-task-backend-api.git```

2. Переход в директорию проекта

```cd cft-task-backend-api```

3. Автоматическая сборка и запуск приложения

```gradlew bootRun``` для cmd

```./gradlew bootRun``` для PowerShell

NOTE: перед установкой убедитесь, что у вас установлен git и JRE
## Endpoints

### Добавление интервала из массивов целых чисел
```
curl -X POST "http://localhost:8080/api/v1/intervals/merge?kind=digits" -H "Content-Type: application/json" -d "[[1, 4], [3, 6], [8, 10]]"
```
### Добавление интервала из массивов букв
```
curl -X POST "http://localhost:8080/api/v1/intervals/merge?kind=digits" -H "Content-Type: application/json" -d "["a", "f"],["d", "j"],["r", "z"]"
```

### Запрос минимального целочисленного интервала
```
curl -X GET "http://localhost:8080/api/v1/intervals/min?kind=digits"
```

### Запрос минимального буквенного интервала
```
curl -X GET "http://localhost:8080/api/v1/intervals/min?kind=letters"
```

## Технологии
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Swagger


