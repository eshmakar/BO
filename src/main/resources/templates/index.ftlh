<#import "parts/common.ftlh" as c> <#-- импортируем bootstrap-->
<@c.page>
    <div xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
        <title>Легкая копия газеты бизнес онлайн</title>
        <h5>Легкая копия газеты бизнес онлайн</h5>
    </div>

    <div>
        <#--    Главная тема-->
        <#list mainNews as main>
        <div class="card my-3">
            <div class="m-2">
                <#if main.photo??>
                    <img src="${main.photo}" width="350"/>
                </#if>
                <h5><a href="/${main.numbersOfLinks}">${main.title}</a> <span class="badge rounded-pill bg-danger">${main.comments} </span></h5>
            </div>
            </#list>
        </div>
    </div>



<#--    <h3>Горячие темы</h3>-->
    <#list hotNews as hot>
        <div class="card my"> <#--размер рамки-->
            <div class="m-1">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <img src="${hot.photo}" width="100"/>
                        </div>
                        <div class="col-7">
                            <span><a href="/${hot.numbersOfLinks}">${hot.title}</a> <a href="google.com"> <span class="badge rounded-pill bg-danger">${hot.comments}</a></span></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    <#--            <div class="container">
                    <div class="row">
                        <div class="col-sm-3">
                            <img src="${hot.photo}" width="100"/>
                        </div>
                        <div class="col-sm-9">
                            <div class="row">
                                <div class="col-8 col-sm-6">
                                    <h6><a href="/${hot.numbersOfLinks}">${hot.title} - ${hot.comments}</a></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->


    <#--            <#if hot.photo??>
                <h6> <img src="${hot.photo}" width="100"/>
                </#if>
                <a href="/${hot.numbersOfLinks}">${hot.title} - ${hot.comments}</a></h6>
            </div>-->



    </#list>



    <div>
        <#--    <h3>Последние темы</h3>-->
        <#list lastNews as last>
            <div class="card my-3">
                <div class="m-2">
                    <#--                    кол-во комментов красным цветом-->
                    <span>${last.time}  <a href="/${last.numbersOfLinks}">${last.title}</a> <a href="google.com"> <span class="badge rounded-pill bg-danger">${last.comments}</a> </span></span>
                </div>
            </div>
        </#list>
    </div>
</@c.page>