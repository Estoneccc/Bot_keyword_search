Задание 2. Singleton

https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/ChatWithBotServiceImpl.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/PersonChatServiceImpl.java
https://github.com/Estoneccc/Bot_keyword_search/blob/task1/task1-Danil/src/main/java/project/services/impl/PersonServiceImpl.java

Эти три класса реализованны как Singleton из-за аннотации @Service. Эта аннотация и другие стереотипные, например такие как @Component, @Repository, @Controller, позволяют Spring автоматически обнаружить и создать бин этого класса, а в Spring видимость бина по умолчанию - Singleton.
