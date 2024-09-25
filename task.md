## Задание 2. Singleton

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/ChatWithBotServiceImpl.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/PersonChatServiceImpl.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/PersonServiceImpl.java

Эти три класса реализованны как Singleton из-за аннотации @Service.
Эта аннотация и другие стереотипные, например
такие как @Component, @Repository, @Controller,
позволяют Spring автоматически обнаружить
и создать бин этого класса, а в Spring видимость бина
по умолчанию - Singleton.

## Задание 3. Prototype

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/Person.java

В этом классе реализован паттерн Prototype,
так как он имеет метод newPerson(), который создает
копию объекта этого класса.

## Задание 4. Static factory method

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/PersonChat.java

В этом классе реализован паттерн Static factory method,
так как он имеет статически фабричный метод getPersonChat(),
который возвращает экземпляр этого класса. Конструкторы при этом
имеют видимость default.
