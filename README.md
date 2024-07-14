### Hexlet tests and linter status:
[![Actions Status](https://github.com/Bookworm47/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Bookworm47/java-project-78/actions)

[![Maintainability](https://api.codeclimate.com/v1/badges/941e331fa6eb6cea0855/maintainability)](https://codeclimate.com/github/Bookworm47/java-project-78/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/941e331fa6eb6cea0855/test_coverage)](https://codeclimate.com/github/Bookworm47/java-project-78/test_coverage)


Data Validator — это библиотека, предназначенная для проверки точности различных типов данных.

Валидатор работает следующим образом: сначала создается объект валидатора.
Далее создается и настраивается схема проверки данных.
После этого выполняется проверка данных с использованием созданной схемы.

Примеры использования:

// String
StringSchema schema = v.string();
schema.isValid(null); // true
schema.required().isValid(null); // false
schema.minLength(5).isValid("hello"); // true

// Number
NumberSchema schema = v.number().required().positive();
schema.isValid(-5); // true
schema.positive().isValid(-5); // false
schema.range(5, 10).isValid(7); // true

// Map
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("age", v.number().range(5, 50));
MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("firstName", "Jhony");
human1.put("age", 33);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("firstName", "");
human2.put("age", 40);
schema.isValid(human1); // false