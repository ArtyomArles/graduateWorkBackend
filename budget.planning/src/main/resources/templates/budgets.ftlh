<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Бюджеты</title>
</head>
<body>
<a href="/" id="back">Назад</a>
<h2>Бюджеты</h2>
<hr>
<form action="/budgets" method="get">
    Поиск по году бюджета: <input type="number" name="year" min="2020" max="2099" step="1"><br>
    <input type="submit" value="Поиск">
</form>
<#list budgets as budget>
<div>
    <b>
        <a href="/budgets/${budget.id}">
            <p>
                ${budget.year}
            </p>
        </a>
    </b>
</div>
<#else>
    <h3>Бюджетов нет</h3>
</#list>

<hr>
<h2>Создание нового бюджета</h2>
<form action="/budgets/create" method="post">
    Год бюджета: <input type="number" name="year" min="2020" max="2099" step="1"/><br><br>
    Сумма бюджета: <input type="number" name="sum" step="any"><br><br>
    <input type="submit" value="Добавить бюджет">
</form>
</body>
</html>
