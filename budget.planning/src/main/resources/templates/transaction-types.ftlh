<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Типы транзакций</title>
</head>
<body>
<a href="/" id="back">Назад</a>
<h2>Типы транзакций</h2>
<hr>
<form action="/transaction-types" method="get">
    Поиск по названию транзакции: <input type="text" name="title"><br>
    <input type="submit" value="Поиск">
</form>
<#list transactionTypes as transactionType>
<div>
    <b>
        <a href="/transaction-types/${transactionType.id}">
            <p>
                ${transactionType.title}
            </p>
        </a>
    </b>
</div>
<#else>
    <h3>Типов транзакций нет</h3>
</#list>

<hr>
<h2>Создание нового типа транзакции</h2>
<form action="/transaction-types/create" method="post">
    Название типа транзакции: <input type="text" name="title"/><br><br>
    <input type="submit" value="Добавить тип транзакции">
</form>
</body>
</html>
