<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Категории</title>
</head>
<body>
<a href="/" id="home">Назад</a>
<h2>Категории</h2>
<hr>
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
</#list>

<hr>
<h2>Создание новой категории</h2>
<form action="/categories/create" method="post">
    Название категории: <input type="text" name="title"/><br><br>
    Описание категории: <input type="text" name="description"><br><br>
    <input type="submit" value="Добавить категорию">
</form>
</body>
</html>
