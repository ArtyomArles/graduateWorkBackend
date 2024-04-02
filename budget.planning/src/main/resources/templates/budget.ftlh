<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Бюджет</title>
</head>
<body>
<a href="/budgets" id="back">Назад</a>
    <h2>Бюджет</h2>
<hr>
<b><span>Год бюджета:</span></b>
<span>${budget.year}</span><br>
<b><span>Транзакции:</span></b><br>
<#list transactions as transaction>
<div style="padding: 5px; margin: auto 0 5px 20px; border:1px solid black; display: inline-block;">
    <b>Название транзакции:</b> <a href="/transactions/${transaction.id}">${transaction.title}</a><br>
    <b>Сумма транзакции: </b>${transaction.sum}<br>
</div><br>
<#else>
<b style="margin-left:20px;">Транзакций нет</b><br>
</#list>
<b><span>Сумма бюджета:</span></b>
<span>${budget.sum}</span><br>
<b><span>Баланс:</span></b>
<span>${budget.balance}</span><br>

<form action="/budgets/delete/${budget.id}" method="post">
    <input type="submit" value="Удалить">
</form>
<hr>
<h2>Редактирование бюджета</h2>
<form action="/budgets/edit/${budget.id}" method="post">
    Год бюджета: <input type="number" id="year" name="year" min="2020" max="2099" step="1"/><br><br>
    Сумма бюджета: <input type="number" id="sum" name="sum" step="any"><br><br>
    <input type="submit" value="Изменить">
</form>
</body>
<script>
    const year = "${budget.year}"
    const inputYear = document.getElementById("year")
    inputYear.value = parseInt(year.replace(/\s/g, ''))

    const sum = "${budget.sum}"
    const inputSum = document.getElementById("sum")
    inputSum.value = parseFloat(sum.replace(/\s/g, '').replace(/,/g, '.'))
</script>
</html>
