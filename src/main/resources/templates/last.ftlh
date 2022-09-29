<#import "parts/common.ftlh" as c>
<@c.page>
    <title>СТАТИСТИКА</title>
    <div style="text-align: center"><h5><b>Статистика</b></h5></div>

    <#if stat??>
        <div>
            <#list stat as l>
                <div>
                    <b>${l.date?datetime?string('dd.MM HH:mm:ss')}</b> - <a href="${l.link}">${l.title} (${l.counterOfSeen})</a>
                </div>
            </#list>
        </div>
    </#if>
</@c.page>




