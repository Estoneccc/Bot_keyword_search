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

## Задание 9. Паттерн Bridge

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/PersonChatServiceImpl.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/CustomPersonChatService.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/repositories/PersonChatRepository.java

Класс PersonChatServiceImpl является абстракцией, так как в нем используется
композиция в виде реализации PersonChatRepository. Класс CustomPersonChatService
является уточненной абстракцией, так как наследуются от абстракции UserInterface.

## Задание 10. Composite

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/MessageComponent.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/MessageComposite.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/MessageLeaf.java

Общий интерфейс для всех элементов MessageComponent является компонентом.
Класс MessageComposite
является узлом, так как в нем реализован метод addMessage()
для создания потомков и метод removeMessage() для их удаления. У класса MessageLeaf
эти методы выбрасывают ошибку,
поэтому у него потомков быть не может. Таким образом класс MessageLeaf является листом.

## Задание 11. Decorator

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/processors/MessageProcessor.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/processors/MessageProcessorDecorator.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/processors/BasicMessageProcessor.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/processors/KeywordFilteringMessageProcessor.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/processors/LoggingMessageProcessor.java

Базовый интерфейс для обработки сообщений, который будет декорироваться,
MessageProcessor, является компонентом. Абстрактный класс
MessageProcessorDecorator является декоратором, так как он наследуется
от компонента и содержит его композицию. Классы KeywordFilteringMessageProcessor и
LoggingMessageProcessor являются конкретными декораторами, так как наследуются от
декоратора. Класс BasicMessageProcessor является конкретным компонентом, так
как наследуется от компонента.

## Задание 12. Facade

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/bot/impl/TelegramBot.java

Класс TelegramBot является фасадом, так как он скрывает сложную логику 
взаимодействия с несколькими сервисами, упрощая код для работы с основными 
функциями бота. Он содержит в себе в виде композиций три работающих класса:
personService, chatWithBotService, personChatService.

## Задание 13. Flyweight

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/Keyword.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/ConcreteKeyword.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/domain/KeywordFactory.java

Три класса: Keyword, ConcreteKeyword и KeywordFactory реализуют паттерн Flyweight.
Интерфейс Keyword представляет общую функциональность для ключевых слов.
Класс ConcreteKeyword хранит данные, которые могут быть общими для разных пользователей.
Фабрика KeywordFactory хранит уникальные объекты ключевых слов, создавая 
их только при необходимости.

## Задание 14. Proxy

Прокси PersonServiceProxy выполняет проверки, прежде чем вызывать методы
реального объекта PersonServiceImpl. PersonServiceImpl обрабатывает логику 
без изменений, оставляя проверку доступа или кэширование на уровне Proxy.
Использование Proxy позволяет добавлять дополнительные функции, такие
как логирование или проверка прав доступа, без изменения кода PersonServiceImpl.

## Задание 15. Chain of responsibility

Интерфейс Handler содержит метод setNext для установки следующего 
обработчика в цепочке и метод getNextHandler для получения следующего обработчика.
Абстрактный класс AbstractHandler реализует логику управления следующим этапом в цепочке,
сохраняя ссылку на следующий обработчик и вызывая его при необходимости.
Конкретные обработчики AccessCheckHandler и KeywordCheckHandler реализуют 
проверки прав доступа пользователя и проверки наличия ключевых слов в сообщении.
Каждый обработчик реализует свою логику проверки, а абстрактный класс AbstractHandler 
управляет запросом следующему обработчику.

## Задание 16. Command

Класс Command является интерфейсом команд, который содержит метод execute().
StartCommand является конкретной реализацией интерфейса Command. Этот класс
реализует конкретные действия для команды "/start", поэтому является конкретной командой.
Класс CommandManager выполняет роль клиента. Он управляет набором команд. Позволяет
зарегистрировать команду и выполнить ее по имени. С помощью него можно добавлять новые
команды или изменять логику обработки без изменения основной структуры.

## Задание 17. Interpreter

Общий интерфейс Expression представляет абстрактное выражение. Класс CommandExpression является
терминальным выражением, который интерпретирует команды. Класс OrExpression комбинирует
нетерминальные выражения с помощью логического оператора OR.
Context представляет собой текст сообщения, который анализируется для выявления команды.

## Задание 18. Iterator

ChatWithBotServiceImpl — это агрегат. Этот класс работает с хранилищем чатов и предоставляет
сервисные методы для их получения. Также он имеет метод getActiveChatIterator(), который возвращает
итератор для обхода активных чатов.
ChatWithBotIterator — это итератор. Он предоставляет способ поочередного доступа к объектам
ChatWithBot из коллекции. Он скрывает детали обхода коллекции, проверяя активность чатов 
в методе hasNext() и предоставляя сам чат в методе next().

## Задание 19. Mediator
ChatMediator - это интерфейс для посредника, который управляет взаимодействием между
различными компонентами. ChatMediatorImpl - это реализация посредника. Этот класс
взаимодействует с базой данных через ChatWithBotServiceImpl и предоставляет итератор.
ChatWithBotServiceImpl — коллега, который отвечает за взаимодействие с базой данных.
ChatIterator и его реализация ChatWithBotIterator — коллега, предоставляющий доступ
к коллекции чатов, управляемой медиатором. ChatWithBot — объекты,
которыми управляет медиатор.

## Задание 20. Memento
Класс Person является хозяином. Он имеет методы saveToMemento() и restoreFromMemento(),
которые сохраняют текущую версию своего состояния и восстанавливают ее соответственно.
PersonCaretaker является хранителем, так как хранит в себе состояние хозяина. Посыльным является
класс SavedPeople, он управляет сохранением хранителей и их восстановлением.

## Задание 21. Observer
Класс Person является наблюдателем, реализующим методы интерфейса Observer.
Метод update(String message) вызывается субъектом, чтобы передать сообщение наблюдателю.
Класс Chat является наблюдаемым объектом, реализующим методы интерфейса Subject.
Метод registerObserver() добавляет наблюдателя в список, метод removeObserver - удаляет,
метод notifyObservers() уведомляет всех зарегистрированных наблюдателей, передавая
им сообщение о событии.

## Задание 22. State
BotState — это общий интерфейс, определяющий метод handle(). Этот метод должен реализоваться для
каждого конкретного состояния. SelectChatState, SelectActiveChatState, ChatSelectedState,
ActiveChatSelectedState это конкретные состояния, каждый из них представляет поведение, характерное для
текущего состояния бота. StateContext хранит текущее состояние бота и данные, которые нужны для работы
состояний (например, ID пользователя, доступные чаты). StateContext является контекстом.

## Задание 23. Strategy
MessageProcessor является контекстом. Он решает, какая стратегия будет использована для обработки
входящего сообщения. MessageProcessingStrategy является интерфейсом стратегии. Он задаёт контракт
для всех стратегий обработки сообщений. StartCommandStrategy, TextMessageStrategy, CallbackQueryStrategy
это конкретные стратегии. Они реализуют обработку конкретных случаев.

## Задание 24. TemplateMethod
AbstractMessageHandler содержит шаблонный метод processMessage(Update update), который определяет
общий процесс обработки сообщений. Методы handlePrivateMessage, handleSuperGroupMessage, handleCallback
делегируют конкретную реализацию подклассам. Подклассы StartCommandHandler и SuperGroupMessageHandler
предоставляют уникальную реализацию абстрактных методов, StartCommandHandler реализует метод handlePrivateMessage,
SuperGroupMessageHandler - реализует handleSuperGroupMessage.

## Задание 25. Visitor
Интерфейс MessageVisitor является посетителем. Он определяет операции, которые можно выполнить с каждым типом элемента.
Интерфейс Visitable добавляется к классам, которые должны быть обработаны посетителем.
Метод accept(MessageVisitor visitor) вызывает у переданного посетителя соответствующий 
метод для обработки. Конкретные элементы PrivateMessage, GroupMessage, CallbackQuery представляют
конкретный тип данных, который должен быть обработан. MessageProcessor является конкретным посетителем.
Этот класс реализует интерфейс MessageVisitor и выполняет конкретные действия для каждого типа элемента.