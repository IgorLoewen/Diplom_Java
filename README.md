# Stellar Burgers: Unit Testing Project

## Описание проекта
Этот проект представляет собой тестирование программы для заказа бургеров в ресторане Stellar Burgers. Основной целью является покрытие юнит-тестами ключевых классов и методов, обеспечивающих функциональность программы.

### Основные классы:
1. **Bun** - модель булочки для бургера.
2. **Ingredient** - модель ингредиента (начинка или соус).
3. **IngredientType** - перечисление типов ингредиентов.
4. **Database** - класс для работы с базой данных, предоставляющий список булочек и ингредиентов.
5. **Burger** - модель бургера, содержащая булочку и ингредиенты.
6. **Praktikum** - точка входа в программу, демонстрирующая процесс создания и заказа бургера.

### Основные задачи:
- Покрыть юнит-тестами ключевые методы.
- Использовать моки, стабы и параметризацию для тестирования.
- Обеспечить покрытие кода тестами не ниже 70%.

## Установленные библиотеки
Для тестирования используются следующие библиотеки:
- **JUnit 4** - для написания и выполнения тестов.
- **Mockito** - для создания моков и проверки взаимодействий между объектами.
- **Jacoco** - для анализа покрытия кода.

## Как запустить тесты

1. **Убедитесь, что Maven установлен в системе:**

   Вы можете проверить установку Maven, выполнив команду:
   ```bash
   mvn -v
   ```

2. **Запустите тесты:**

   Перейдите в корневую директорию проекта и выполните команду:
   ```bash
   mvn clean test
   ```
   Эта команда выполнит все юнит-тесты, а результат будет выведен в консоль.

3. **Проверьте результаты выполнения тестов:**

   В конце выполнения команды вы увидите статистику, подобную следующей:
   ```
   Tests run: 36, Failures: 0, Errors: 0, Skipped: 0
   ```
   Это означает, что все тесты успешно выполнены.

## Как сгенерировать и открыть отчет Jacoco

1. **Сгенерируйте отчет Jacoco:**

   Выполните следующую команду:
   ```bash
   mvn clean verify
   ```
   В процессе выполнения Jacoco создаст отчет о покрытии кода.

2. **Найдите отчет:**

   Отчет сохраняется в папке `docs` в корневой директории проекта. Основной файл:
   ```
   docs/index.html
   ```
   Или откройте онлайн-отчет Jacoco по адресу: [https://igorloewen.github.io/Diplom_Java/](https://igorloewen.github.io/Diplom_Java/) для просмотра покрытия кода.

3. **Откройте отчет:**

   Откройте файл `index.html` в любом браузере. Вы увидите детализированный отчет о покрытии кода с разбивкой по классам, методам и строкам кода.

   Например, на macOS:
   ```bash
   open docs/index.html
   ```
   Или на Windows:
   ```bash
   start docs\index.html
   ```

## Структура проекта
```
├── src
│   ├── main
│   ├── test
├── docs
├── pom.xml
└── README.md
```

## Пример вывода программы
После запуска программы (`Praktikum.main`), в консоли будет выведен рецепт собранного бургера, например:
```
(==== black bun ====)
= sauce sour cream =
= filling dinosaur =
= filling cutlet =
(==== black bun ====)

Price: 600.00
```

