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

## Задание 5. Паттерн Builder

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/Chat.java

В классе Chat реализован паттерн Builder. В этом классе имеется
внутренний класс ChatBuilder, который является билдером.
Он повторяет поля класса Chat и имеет методы, работающие
с этими полями. Также конструктор класса Chat приватный,
поэтому его нельзя будет вызвать вне класса. В классе Chat
имеется статический метод
public static ChatBuilder having() {
return new ChatBuilder();
который возвращает билдер, а метод в классе ChatBuilder
public Chat done() {
return new Chat(name, chatId);
}
возвращает готовый экземпляр класса Chat.

## Задание 6. Паттерн Factory method

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/Human.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/HumanGenerator.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/Man.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/ManGenerator.java

Интерфейс Human является продуктом. Абстрактный класс
HumanGenerator является создателем. Класс ManGenerator
является конкретным создателем. Класс Man является конкретным
продуктом.
В абстрактном классе HumanGenerator (создатель) имеется
фабричный метод newHuman(), который возвращает объект
Human (продукт). В классе ManGenerator (конкретный создатель)
этот метод переопределяется и возвращает конкретный продукт Man.

## Задание 7. Паттерн Abstract factory

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/ChatFactory.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/DefaultChatFactory.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/ChatEntity.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/Chat.java

Интерфейс ChatFactory является абстрактной фабрикой,
так как имеет только те методы, которые создают какие-то объекты,
то есть продукты. Класс DefaultChatFactory реализует интерфейс
ChatFactory, и поэтому является конкретной фабрикой. Класс
ChatEntity, является продуктом, так как его создает фабрика.
А класс Chat, является конкретным продуктом, так как он
реализует интерфейс продукта ChatEntity.

## Задание 8. Паттерн Adapter

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/PersonService.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/PersonServiceImpl.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/repositories/PersonRepository.java

Интерфейс PersonService является таргетом. PersonRepository - это
адаптируемый интерфейс, который реализует конкретные методы для
работы с базой. Класс PersonServiceImpl - это реализация PersonService,
которая использует PersonRepository для выполнения операций. Этот класс
является адаптером, так как наследуется от PersonService и содержит в себе экземпляр
PersonRepository, как композицию.