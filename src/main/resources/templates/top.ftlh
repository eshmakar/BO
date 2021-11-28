<div>
    <title>Популярные статьи</title>
    <h1>Популярные статьи</h1>
    <style>
        a {
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
            color: #000 !important; /* Отменяем цвет у ссылки */
        }
    </style>
</div>
<div>
    <#--    Главная тема-->
    <#list tops as top>
    <div class="card my-3">
        <div class="m-2">
            <span><a href="/${top.numbersOfLinks}">${top.title}</a></span>
            <span> - ${top.comments}</span>
        </div>
        </#list>
    </div>
</div>