<div>
    <title>Легкая копия газеты бизнес онлайн</title>
    <h1>Легкая копия газеты бизнес онлайн</h1>
    <h3><a href="/top">10 популярных новостей</a></h3>
    <style>
        a {
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
            color: #000 !important; /* Отменяем цвет у ссылки */
        }
    </style>
</div>
<div>
    <#--    Главная тема-->
    <#list mainNews as main>
    <div class="card my-3">
        <div class="m-2">
            <img src="${main.photo}" width="400"/>
            <h2><a href="/${main.numbersOfLinks}">${main.title}</a></h2>
            <h4>${main.comments}</h4>
        </div>
        </#list>
    </div>
</div>
<div>
    <#--    <h3>Горячие темы</h3>-->
    <#list hotNews as hot>
    <div class="card my-3">
        <div class="m-2">
            <#if hot.photo??>
                <img src="${hot.photo}" width="100">
            </#if>
            <a href="/${hot.numbersOfLinks}">${hot.title}</a>
            <span> - ${hot.comments}</span>
        </div>
        </#list>
    </div>
</div>
<div>
    <br><br><br>
    <#--    <h3>Последние темы</h3>-->
    <#list lastNews as last>
    <div class="card my-3">
        <div class="m-2">
            <span>${last.time}</span>
            <a href="/${last.numbersOfLinks}">${last.title}</a>
            <span> - ${last.comments}</span>
        </div>
        </#list>
    </div>
</div>


<#--<#list mainNews as user>-->
<#--    <div>-->
<#--        ${user}<#sep>, </#sep>-->
<#--    </div>-->
<#--</#list>-->

<#--<#list mainNews as x>-->
<#--    ${x_index + 1}. ${x}<#if x_has_next>,</#if>-->
<#--</#list>-->