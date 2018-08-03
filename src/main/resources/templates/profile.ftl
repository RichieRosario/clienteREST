<html>
<head>
    <title>Cliente REST</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> </head>



<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/"style="color:white">Cliente REST</a>
        </div>

        <ul class="nav navbar-nav " style="text-decoration:none">

        </ul>
    </div>
</nav>


<body class="bg-light" style="position:relative" >
    <div class="container">




    <br>

    <!-- Tab panes -->

                            <div class="card mx-auto" style="width:75%">
                                <div class="card-header bg-dark"><p class="text-white">Publicaciones</p></div>
                                   <div class="card-body">
                                     <#list muroentradas as entradas>
                                    <div class="card">
                                    <div class="card-header">${entradas.getUser()}
                                    </div>
                                        <div class="card-body">
                                           <p> Personas en esta entrada:
                                            <#if (entradas.getTag())??>
                                               ${entradas.getTag()}
                                            <#else>
                                                Nadie.

                                            </#if></p>
                                            <p>${entradas.getCuerpo()}</p>

                                        <#if entradas.getFoto()??>
                                        <img src="data:image/jpeg;base64, ${entradas.getFoto()}" class="img-thumbnail" style="height:200px;width:auto; max-width:200px;">
                                    </#if>

                                        </div>

                                    </div>

                                     </#list>











    </div>
    </div>
    </div>


    <br>
    </body>

     <footer class="main-footer bg-dark text-white" style="position:fixed;height:32px;width:100%;bottom:0;">

            <p>Cliente REST - Ricardo y Emilio &copy; 2018 </p>
       </footer>

   </html>
