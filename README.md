Есть список сотрудников управления - 4 человека:
Id - 5200005, 5200015,5200010, 5200030
Password - 5, 15, 10, 30 соответственно.
При запуске попадаем на страницу проверки логина и пароля. 
Авторизуемся(логгирует в файл - slf4jlogback.log, находящийся в корне проекта, 3 состояния - успешный вход, 
неуспешная попытка входа и выход).
Если ты начальник, то тебе показывает всех сотрудников управления, если нет - то только тебя.
Тесты написаны, jacoco покрытие показывает, реквесты созданы.