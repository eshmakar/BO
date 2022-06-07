<#import "parts/common.ftlh" as c> <#-- импортируем bootstrap-->
<@c.page>
    <div xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
        <title>Легкая копия газеты бизнес онлайн</title>
        <div style="text-align: center"><h5><b>Лёгкая копия газеты БО без реклам</b></h5></div>
    </div>


    <div>
        <#--    Главная тема-->
        <#if mainNews??>
            <#list mainNews as main>
                <div style="text-align: center"><img src="${main.photo}" class="img-fluid" alt="Responsive image">
                    <h5><a href="/${main.numbersOfLinks}">${main.title}</a>
                        <span class="badge rounded-pill bg-danger"><b>${main.comments}</span></h5>
                </div>
            </#list>
        </#if>
    </div>


    </div>

<#--    <h3>Горячие темы</h3>-->
    <#if hotNews??>
        <#list hotNews as hot>
            <div class="card my"> <#--размер рамки-->
                <div class="m-1">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <img src="${hot.photo}" width="100"/>
                            </div>
                            <div class="col-7">
                                <b> <a href="/${hot.numbersOfLinks}">${hot.title}</a>
                                    <span class="badge rounded-pill bg-danger">${hot.comments}</span></b>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </#if>


    <div>
        <#--    <h3>Последние темы</h3>-->
        <#if lastNews??>
            <#list lastNews as last>
                <div class="card my-3">
                    <div class="m-2">
                        <#--                        кол-во комментов красным цветом&ndash;&gt;-->
                        <b>${last.time}</b>
                        <a href="/${last.numbersOfLinks}">${last.title}</a>
                        <span class="badge rounded-pill bg-danger">${last.comments}</span>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</@c.page>