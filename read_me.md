Информация о проекте "Piano test task"

Общее описание системы
Веб приложение, позволяющее отображать результаты поиска по сайту api.stackexchange.com, данные поиска получаем посредством rest запросов, отображаем с помощью spring web+thymeleaf.  
 
Стартовая страница вебсервера http://localhost:8080/
Ссылка на проект https://github.com/MikeStrizhov/piano 

В проекте использовались 
spring boot, spring web и rest template, thymeleaf и css стили bootstrap; для тестов JUnit, Mockito и WebMvcTest

Общая информация о системе
Версия java 1.8, сборщик maven

Так как задача разработчика не ограничивается написанием кода, но включает в себя помощь другим членам команды, в том числе руководству проектом, менеджерам по продажам, то я бы внес следующие предложения по возможностям дальнейшего развития проекта:

- Расширить функционал, в частности, дополнить веб интерфейс возможностям кастмизировать поисковую выдачу, задать порядок сортировки и так далее...
- Добавить поддержку интернационализации для фронтэнда
- Добавть покрытие автотестами веб слоя с помощью selenium
- Использовать lombok для автогенерации кода в объектах, представляющих модель.
В рамках текущей задачи, я посчитал использование lombok избыточным, но при изменении требований к проекту, в частности, изменении состава передаваемых данных, этот инструмент будет полезным для ускорении работы и уменьшения количества потенциальных ошибок.
- Переписать приложение в парадигме реактивного программирования с целью увеличения производительности 
Использовать web flux.
- Добавить кэширование, секюрити и т.д.

# Текст задания

Full Stack Live Coding Exercise

Scenario
Create a Java web application with a form that allows a user to enter a search string, queries Stack Exchange 
to find questions with titles containing that string, and display the results.

Requirements
Display the results in a tabular format showing at least the date of the question, its title and who posted it.
If the question has been answered, use a visual style to differentiate it from unanswered questions.
The displayed item should have a means to navigate to the original question on Stack Exchange.

Expectations
Treat this project as you would any professional task.
You may use any resource, library, example code or documentation you can find on the web.

Documentation
Stackoverflow API
[Stackoverflow API] (http://api.stackexchange.com/docs/search#order=desc&sort=activity&intitle=java&filter=default&site=stackoverflow&run=true)

