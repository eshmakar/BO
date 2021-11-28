<style>
    a {
        text-decoration: none; /* Отменяем подчеркивание у ссылки */
        color: #000 !important; /* Отменяем цвет у ссылки */
    }
</style>
<div xmlns="http://www.w3.org/1999/html">
    <div class="card my-3">
        <div class="m-2">
            <#if contentFromUrl.zagolovok??>
                <h2><span>${contentFromUrl.zagolovok}</span></h2>
                <title>${contentFromUrl.zagolovok}</title>
            </#if>
            <div>
                <#if contentFromUrl.photo??>
                    <img src="${contentFromUrl.photo}" width="350"/>
                </#if>
            </div>
        </div>
    </div>
</div>
<div>
    <#list telo as t>
    <div class="card my-3">
        <div class="m-2">
                <span>${t}</span>
            <br><br>
        </div>
        </#list>
    </div>
</div>