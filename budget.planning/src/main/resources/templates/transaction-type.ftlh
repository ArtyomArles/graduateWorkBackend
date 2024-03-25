<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тип транзакции</title>
</head>
<body>
<a href="/transaction-types" id="back">Назад</a>
    <h2>Тип транзакции</h2>
<hr>
<b><span>Название:</span></b>
<span>${transactionType.title}</span><br>

<form action="/transaction-types/delete/${transactionType.id}" method="post">
    <input type="submit" value="Удалить">
</form>
<hr>
<h2>Редактирование типа транзакции</h2>
<form action="/transaction-types/edit/${transactionType.id}" method="post">
    Название типа транзакции: <input type="text" name="title" value="${transactionType.title}"><br><br>
    <input type="submit" value="Изменить">
</form>
</body>
</html>
