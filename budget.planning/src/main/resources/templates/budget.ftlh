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
<div>
    <b>
        <a href="/transactions/${transaction.id}">
                ${transaction.title}
        </a>
    </b>
</div>
<#else>
<b>Транзакций нет</b><br>
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
