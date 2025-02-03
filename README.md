Запуск: Чтобы запустить тесты с нужным браузером:

Для Chrome: mvn test -Dbrowser=chrome
Для Yandex: mvn test -Dbrowser=yandex

для обеих
mvn clean test -Dbrowser=chrome && mvn test -Dbrowser=yandex
# README.md

## Allure Report: Генерация и просмотр

Этот проект использует **Allure** для генерации отчетов о тестировании. Результаты сохраняются в папке `target/allure-results`, а сгенерированные отчеты — в `target/allure-report`.

---

### 1. Подготовка

Убедитесь, что Allure установлен на вашем компьютере. Для установки используйте инструкции для вашей ОС:

#### Для Windows:
1. Скачайте [Allure Commandline](https://github.com/allure-framework/allure2).
2. Разархивируйте архив и добавьте путь до папки `bin` в переменную окружения `PATH`.

#### Для macOS:
```bash
brew install allure
```

#### Для Linux:
```bash
sudo apt install allure
```

---

### 2. Генерация результатов тестирования

1. Запустите тесты:
   ```bash
   mvn clean test
   ```
   После выполнения тестов результаты будут сохранены в папке:
   ```
   target/allure-results
   ```

---

### 3. Генерация отчета Allure

Чтобы создать HTML-отчет из сохраненных результатов:
```bash
allure generate target/allure-results -o target/allure-report --clean
```
Флаг `--clean` очищает папку `target/allure-report` перед генерацией нового отчета.

---

### 4. Просмотр отчета Allure

Для запуска локального веб-сервера и просмотра отчета в браузере:
```bash
allure serve target/allure-results
```
Этот метод автоматически создаёт временный HTML-отчет и открывает его в вашем браузере.

Если вы уже сгенерировали отчет (см. п.3) и хотите открыть его из папки:
```bash
allure open target/allure-report
```

---

### 5. Git и Allure

#### Обновление `.gitignore`

Добавьте в `.gitignore` следующее:

```plaintext
# Исключаем временные файлы и кэш Allure
**/allure-results/*
**/allure-report/history/*
!**/allure-report/
!**/allure-results/
```

Эти команды:
1. **Исключают временные файлы** из `allure-results` и `allure-report`, которые не нужны для анализа.
2. **Сохраняют необходимые файлы**, такие как структура отчета и результаты, для передачи на GitHub.

---

### 6. Загрузка результатов и отчетов на GitHub

После изменения `.gitignore` и генерации отчетов выполните команды для загрузки данных на GitHub:

```bash
git add target/allure-results
git add target/allure-report
git commit -m "Добавлены Allure Results и Allure Report"
git push origin main
```

Теперь Allure Results и Report будут добавляться в ваш репозиторий и сохраняться между запусками.
