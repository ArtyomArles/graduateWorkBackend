<!DOCTYPE html>
<html
        lang="en"
        xmlns="http://www.w3.org/1999/xhtml"
        xmlns:th="http://www.thymeleaf.org"
>
<head>
    <meta charset="UTF-8">
    <title>Категория бюджета</title>
</head>
<body>
<a href="/categories" id="categories">Назад</a>
    <h2>Категория бюджета</h2>
<hr>
<b><span>Название:</span></b>
<span>${category.title}</span><br>
<b><span>Описание:</span></b>
<span>${category.description}</span><br>

<form action="/categories/delete/${category.id}" method="post">
    <input type="submit" value="Удалить">
</form>
<hr>
<h2>Редактирование категории бюджета</h2>
<form action="/categories/edit/${category.id}" method="post">
    Название категории: <input type="text" name="title" value="${category.title}"><br><br>
    Описание категории: <input type="text" name="description" value="${category.description}"><br><br>
    <input type="submit" value="Изменить">
</form>
</body>
</html>
