<#import "parts/common.ftlh" as c>
<@c.page>
    <#if content??>
        <div>
            <#if content.zagolovok??>
                <h4><b>${content.zagolovok}</b></h4>
                <title>${content.zagolovok}</title>
            </#if>
        </div>
        <br>
        <div>
            <#if content.telo??>
                <#list content.telo as t>
                    <br>
                    <#if t?starts_with("https://stcdn")>
                        <img src="${t}" width="350"/>
                    <#elseif t?starts_with("https://vk.com")>
                        <div style="text-align: center;"><a class="navbar-brand" href="${t}"><img
                                        src="https://mnogo-golosov.ru/wp-content/uploads/2018/11/47.jpg" alt=""
                                        height="80"></a>
                        </div>
                    <#else>
                        <span>${t}</span><br>
                    </#if>
                </#list>
            </#if>
        </div>

        <div>
            <#if content.commentsCount??>
                <div style="text-align: center;">
                    <div class="m-2">
                        <h4><a href="/comments"><span
                                        class="badge rounded-pill bg-danger"><b>${content.commentsCount}</span></a></h4>
                    </div>
                </div>
            </#if>
        </div>
    </#if>
</@c.page>