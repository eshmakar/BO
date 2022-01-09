<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        <#if content.zagolovok??>
            <h4>${content.zagolovok}</h4>
            <title>${content.zagolovok}</title>
        </#if>
    </div>
    <br>
    <div>
        <#if content.telo??>
            <#list content.telo as t>
                <br>
                <#if t?starts_with("http")>
                    <img src="${t}" width="350"/>
                <#else>
                    <span>${t}</span><br>
                </#if>
            </#list>
        </#if>
    </div>
</@c.page>