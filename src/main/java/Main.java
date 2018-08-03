import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import encapsulacion.PostService;
import freemarker.template.Configuration;
import freemarker.template.Version;
import org.json.JSONObject;
import org.json.JSONString;
import spark.ModelAndView;

import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Session;
import spark.utils.IOUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.text.*;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Cookie;
import javax.servlet.http.Part;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import com.mashape.unirest.http.*;

import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class Main{


    public static void main(String[] args) throws UnirestException, IOException {


        staticFileLocation("/public");
        final Configuration configuration = new Configuration(new Version(2, 3, 26));
        Spark.port(6789);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        File uploadDir = new File("upload");
        uploadDir.mkdir(); //











        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);

        post("/listado", (request,response) -> {


            Map<String, Object> attributes = new HashMap<>();
            String username = request.queryParams("username");
            response.redirect("/listado/"+username);

            return new ModelAndView(attributes, "index.ftl");


        }, freeMarkerEngine);

        get("/listado/:username", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            List<PostService> entradas = new ArrayList<>();
            HttpResponse<String> jsonResponse = Unirest.get("http://localhost:4567/rest/publicaciones/{username}")
                    .routeParam("username",request.params("username"))
                    .header("Accept","application/json")
                    .asString();

            Gson n = new Gson();
            PostService[] prueba = n.fromJson(jsonResponse.getBody(),PostService[].class);
            entradas= Arrays.asList(prueba);

            attributes.put("muroentradas",entradas);


            return new ModelAndView(attributes, "profile.ftl");
        }, freeMarkerEngine);

        post("/addPost", "multipart/form-data", (request, response) -> {
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
            long maxFileSize = 100000000;
            long maxRequestSize = 100000000;
            int fileSizeThreshold = 1024;

            MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                    uploadDir.getAbsolutePath(), maxFileSize, maxRequestSize, fileSizeThreshold);
            request.raw().setAttribute("org.eclipse.jetty.multipartConfig",
                    multipartConfigElement);

            Part uploadedFile = request.raw().getPart("uf");
            if(!uploadedFile.getSubmittedFileName().isEmpty()) {

                String fName = request.raw().getPart("uf").getSubmittedFileName();

                Path out = Paths.get(uploadDir.getCanonicalPath() + "/" + fName);
                InputStream in = uploadedFile.getInputStream();
                Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
                uploadedFile.delete();

                multipartConfigElement = null;
                uploadedFile = null;

                BufferedImage imagen = null;
                File here = new File(".");

                String path = uploadDir.getCanonicalPath() + "/" + fName;
                System.out.println(path);

                try {
                    imagen = ImageIO.read(new File((path)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream imagenb = new ByteArrayOutputStream();
                try {
                    ImageIO.write(imagen, "jpg", imagenb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean autenticado = false;
                String target = request.queryParams("target");
                String tag = request.queryParams("tag");
                String texto = request.queryParams("muro");
                PostService nuevo = new PostService();
                nuevo.setCuerpo(texto);
                nuevo.setTag(tag);
                nuevo.setUser(target);
                nuevo.setFoto(Base64.getEncoder().encodeToString(imagenb.toByteArray()));

                Gson n = new Gson();
                HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:4567/rest/publicaciones/")
                        .header("Accept","application/json")
                        .header("Content-Type","application/json")
                        .body(n.toJson(nuevo))
                        .asJson();
                System.out.println(jsonResponse.getBody());
                response.redirect("/");
            }
            else{

                String target = request.queryParams("target");
                String tag = request.queryParams("tag");
                String texto = request.queryParams("muro");
                PostService nuevo = new PostService();
                nuevo.setCuerpo(texto);
                nuevo.setTag(tag);
                nuevo.setUser(target);
                Gson n = new Gson();
                HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:4567/rest/publicaciones/")
                        .header("Accept","application/json")
                        .header("Content-Type", "application/json")
                        .body(n.toJson(nuevo))
                        .asJson();

                System.out.println(jsonResponse.getBody());
                response.redirect("/");

            }
            return "OK";
        });



    }


}

