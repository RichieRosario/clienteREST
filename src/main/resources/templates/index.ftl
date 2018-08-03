<html>
 <head>
        <title>Cliente REST</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> </head>
    <body>


    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/"style="color:white">Cliente REST</a>
            </div>

            <ul class="nav navbar-nav " style="text-decoration:none">

            </ul>
        </div>
    </nav>
    </body>
 <br>

        <form method="post" action="/listado">
            <div class="card mx-auto" style="width:50%">
                <div class="card-body">
            <input type="text" placeholder="Usuario a Buscar" name="username" id="username" class="form-control" required>
                    <div class="modal-footer">
            <button class="btn btn-info">Listar Publicaciones</button></div>
                </div>
                </div>
        </form>

 <div class="card mx-auto" style="width:50%">
     <div class="card-header bg-dark">
         <p class="text-white">Publicación</p>
     </div>
     <div class="card-body">
         <form method="post"  enctype='multipart/form-data' action="/addPost">
             <input type="text" id="target" name="target" placeholder="Usuario al cual se dirige" class="form-control" required>
             <textarea id="muro" name="muro" class="mx-auto form-control" placeholder="Escribele algo a un usuario..." rows="5" cols="65" ></textarea>

             <input type='file' id="uf" name='uf' accept=".jpg, .jpeg, .png">
             <input type="text" id="tag" name="tag" placeholder="Usuario etiquetado en la entrada" class="form-control">


     </div>
     <div class="modal-footer">

         <button type="submit" class="btn btn-info btn-xs">Publicar</button>
         </form>
     </div>

 </div>

    <footer class="main-footer bg-dark text-white" style="position:fixed;height:32px;width:100%;bottom:0;">

            <p>Cliente REST - Ricardo y Emilio &copy; 2018 </p>
       </footer>
<br></body>
</html>