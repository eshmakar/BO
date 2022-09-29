<#import "parts/common.ftlh" as c> <#-- импортируем файл common.ftlh и назначаем алиас с-->
<@c.page>

    <div>
        <title>Популярные статьи</title>
        <h3>Популярные статьи</h3>
    </div>
    <div>
        <#--    Главная тема-->
        <#list tops as top>
            <div class="card my-3">
                <div class="m-2">
                    <span><a href="/${top.numbersOfLinks}">${top.title}</a> <span class="badge rounded-pill bg-danger">${top.comments}</span></span>
                </div>
            </div>
        </#list>
    </div>
</@c.page>