Запуск: Чтобы запустить тесты с нужным браузером:

Для Chrome: mvn test -Dbrowser=chrome
Для Yandex: mvn test -Dbrowser=yandex

для обеих
mvn clean test -Dbrowser=chrome && mvn test -Dbrowser=yandex
