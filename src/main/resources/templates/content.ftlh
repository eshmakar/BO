<div xmlns="http://www.w3.org/1999/html">
    <div class="card my-3">
        <div class="m-2">
            <h2><span>${contentFromUrl.zagolovok}</span></h2>
            <br>
            <br>
            <image src="${contentFromUrl.photo}" class="some"/>
            <br>
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