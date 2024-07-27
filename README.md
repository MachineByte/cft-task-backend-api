# Тестовое задание ШИФТ ЦФТ

## Описание
Проект включает создание веб-приложения на языке Java, 
которое предназначено для обработки интервалов чисел или букв. 
Основные функции приложения включают объединение пересекающихся интервалов и 
сохранение непересекающихся интервалов в памяти SQL-базы данных (H2). 
Также, приложение может предоставлять минимальный интервал из базы 
данных по запросу.

## Конфигурация и запуск (Windows)
1. Клонирование репозитория

```git clone https://github.com/MachineByte/cft-task-backend-api.git```

2. Переход в директорию Oxygen

```cd cft-task-backend-api```

3. Автоматическая сборка и запуск приложения

```gradlew bootRun``` для cmd

```./gradlew bootRun``` для PowerShell

## Endpoints

Добавление интервала из массивов целых чисел
```
curl -X POST "http://localhost:8080/api/v1/intervals/merge?kind=digits" -H "Content-Type: application/json" -d "[[1, 4], [3, 6], [8, 10]]"
```
Добавление интервала из массивов букв
```
curl -X POST "http://localhost:8080/api/v1/intervals/merge?kind=digits" -H "Content-Type: application/json" -d "["a", "f"],["d", "j"],["r", "z"]"
```

Запрос минимального целочисленного интервала
```
curl -X GET "http://localhost:8080/api/v1/intervals/min?kind=digits"
```

Запрос минимального буквенного интервала
```
curl -X GET "http://localhost:8080/api/v1/intervals/min?kind=letters"
```

