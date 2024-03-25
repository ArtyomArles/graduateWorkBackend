<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Категории бюджета</title>
</head>
<body>
<a href="/" id="back">Назад</a>
<h2>Категории бюджета</h2>
<hr>
<form action="/categories" method="get">
    Поиск по названию категории: <input type="text" name="title"><br>
    <input type="submit" value="Поиск">
</form>
<#list categories as category>
<div>
    <b>
        <a href="/categories/${category.id}">
            <p>
                ${category.title}
            </p>
        </a>
    </b>
</div>
<#else>
    <h3>Категорий бюджета нет</h3>
</#list>

<hr>
<h2>Создание новой категории бюджета</h2>
<form action="/categories/create" method="post">
    Название категории: <input type="text" name="title"/><br><br>
    Описание категории: <input type="text" name="description"><br><br>
    <input type="submit" value="Добавить категорию бюджета">
</form>
</body>
</html>
