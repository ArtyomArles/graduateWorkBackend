<!DOCTYPE html>
<html
        lang="en"
        xmlns="http://www.w3.org/1999/xhtml"
        xmlns:th="http://www.thymeleaf.org"
>
<head>
    <meta charset="UTF-8">
    <title>Категория</title>
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

</body>
</html>
