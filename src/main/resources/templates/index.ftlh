<div>
    <title>Легкая копия газеты бизнес онлайн</title>
    <h1>Легкая копия газеты бизнес онлайн</h1>
</div>
<div>
    <h2>Главная тема</h2>
    <#list mainNews as main>
    <div class="card my-3">
        <div class="m-2">
            <a href="${main.link}">${main.title}</a>
            <span> - ${main.comments}</span>
        </div>
        </#list>
    </div>
</div>
<div>
    <h3>Горячие темы</h3>
    <#list hotNews as hot>
    <div class="card my-3">
        <div class="m-2">
            <a href="${hot.link}">${hot.title}</a>
            <span> - ${hot.comments}</span>
        </div>
        </#list>
    </div>
</div>
<div>
    <h3>Последние темы</h3>
    <#list lastNews as last>
    <div class="card my-3">
        <div class="m-2">
            <span>${last.time}</span>
            <a href="${last.link}">${last.title}</a>
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