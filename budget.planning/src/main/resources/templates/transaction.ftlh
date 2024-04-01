<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Транзакция</title>
</head>
<body>
    <a href="/transactions" id="back">Назад</a>
        <h2>Транзакция</h2>
    <hr>
    <b><span>Название:</span></b>
    <span>${transaction.title}</span><br>
    <b><span>Описание:</span></b>
    <span>${transaction.description}</span><br>
    <b><span>Категория транзакции:</span></b>
    <span>${currentCategory.title}</span><br>
    <b><span>Сумма:</span></b>
    <span>${transaction.sum}</span><br>
    <b><span>Тип транзакции:</span></b>
    <span>${currentTransactionType.title}</span><br>
    <b><span>Дата транзакции:</span></b>
    <span>${transaction.transactionDate}</span><br>


    <form action="/transactions/delete/${transaction.id}" method="post">
        <input type="submit" value="Удалить">
    </form>
    <hr>
    <h2>Редактирование транзакции</h2>
    <form action="/transactions/edit/${transaction.id}" method="post">
        Название транзакции: <input type="text" name="title" value="${transaction.title}"/><br><br>
        Описание транзакции: <input type="text" name="description" value="${transaction.description}"><br><br>
        Категория транзакции:
        <select name="categoryId">
            <option value="${currentCategory.id}">${currentCategory.title}</option>
            <#list categories as category>
                <#if currentCategory.id != category.id>
                   <option value="${category.id}">${category.title}</option>
                </#if>
            </#list>
        </select><br><br>
        Сумма: <input type="number" name="sum" id="sum" step="any"><br><br>
        Тип транзакции:
        <select name="transactionType">
            <option value="${currentTransactionType.id}">${currentTransactionType.title}</option>
            <#list transactionTypes as transactionType>
                <#if currentTransactionType.id != transactionType.id>
                    <option value="${transactionType.id}">${transactionType.title}</option>
                </#if>
            </#list>
        </select><br><br>
        Дата транзакции: <input type="date" name="transactionDate" id="transactionDate" value="${transactionDate}"><br><br>
        <input type="submit" value="Изменить">
    </form>
</body>
<script>
    const sum = "${transaction.sum}"
    const inputSum = document.getElementById("sum")
    inputSum.value = parseFloat(sum.replace(/\s/g, '').replace(/,/g, '.'))
</script>
</html>
