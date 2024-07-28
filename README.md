# �������� ������� ���� ���

## ��������
������ ������������ ����� ���-���������� �� ����� Java, ��������������� ��� ��������� ���������� ����� ��� ����. �������� ������� ���������� ��������:

- ����������� �������������� ����������.
- ���������� ���������������� ���������� � ������ SQL-���� ������ (H2).
- �������������� ������������ ��������� �� ���� ������ �� �������.
- 
## ������������ � ������ (Windows)
1. ������������ �����������

```git clone https://github.com/MachineByte/cft-task-backend-api.git```

2. ������� � ���������� �������

```cd cft-task-backend-api```

3. �������������� ������ � ������ ����������

```gradlew bootRun``` ��� cmd

```./gradlew bootRun``` ��� PowerShell

NOTE: ����� ���������� ���������, ��� � ��� ���������� git � JRE
## Endpoints

### ���������� ��������� �� �������� ����� �����
```
curl -X POST "http://localhost:8080/api/v1/intervals/merge?kind=digits" -H "Content-Type: application/json" -d "[[1, 4], [3, 6], [8, 10]]"
```
### ���������� ��������� �� �������� ����
```
curl -X POST "http://localhost:8080/api/v1/intervals/merge?kind=digits" -H "Content-Type: application/json" -d "["a", "f"],["d", "j"],["r", "z"]"
```

### ������ ������������ �������������� ���������
```
curl -X GET "http://localhost:8080/api/v1/intervals/min?kind=digits"
```

### ������ ������������ ���������� ���������
```
curl -X GET "http://localhost:8080/api/v1/intervals/min?kind=letters"
```

## ����������
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Swagger


