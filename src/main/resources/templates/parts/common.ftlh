<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel = "icon" href =
        "https://m.business-gazeta.ru/icon180x180.png"
              type = "image/x-icon">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <style>
            .turbolinks-progress-bar { /* полоска загрузки страницы*/
                height: 2px;
                background-color: navy;
            }

            a {
                text-decoration: none; /* Отменяем подчеркивание у ссылки */
                color: #000 !important; /* Отменяем цвет у ссылки */
            }
        </style>
        <script defer src="https://cdnjs.cloudflare.com/ajax/libs/turbolinks/5.2.0/turbolinks.js"></script> <#--супер ускорение загрузки страниц, турболинк-->
    </head>
    <body>
    <div class="container mt-2"> <#--для выравнивания контента, чтобы вплотную не прикосался к левому краю экрана mt-2 означает, сколько отсупов сделать после-->
        <#include "navbar.ftlh">
        <#nested>
    </div>
    </body>
    </html>
</#macro>