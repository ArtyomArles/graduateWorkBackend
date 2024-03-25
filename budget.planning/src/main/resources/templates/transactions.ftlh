<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Транзакции</title>
</head>
<body>
<a href="/" id="back">Назад</a>
<h2>Транзакции</h2>
<hr>
<form action="/transactions" method="get">
    Поиск по названию транзакции: <input type="text" name="title"><br>
    <input type="submit" value="Поиск">
</form>
<#list transactions as transaction>
<div>
    <b>
        <a href="/transactions/${transaction.id}">
            <p>
                ${transaction.title}
            </p>
        </a>
    </b>
</div>
<#else>
    <h3>Транзакций нет</h3>
</#list>

<hr>
<h2>Создание новой транзакции</h2>
<form action="/transactions/create" method="post">
    Название транзакции: <input type="text" name="title"/><br><br>
    Описание транзакции: <input type="text" name="description"><br><br>
    Категория транзакции:
    <select name="categoryId">
        <#list categories as category>
            <option value="${category.id}">${category.title}</option>
        </#list>
    </select><br><br>
    Сумма: <input type="number" min="0" name="sum"><br><br>
    Тип транзакции:
    <select name="transactionType">
        <#list transactionTypes as transactionType>
        <option value="${transactionType.id}">${transactionType.title}</option>
    </#list>
    </select><br><br>
    Дата транзакции: <input type="date" name="transactionDate"><br><br>
    <input type="submit" value="Добавить транзакцию">
</form>
</body>
</html>
