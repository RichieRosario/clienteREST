package encapsulacion;

public class PostService {


    private String foto;

    private String cuerpo;

    private String user;

    private String etiqueta;

    public void setFoto(String foto){
        this.foto = foto;
    }


    public String getFoto(){
        return foto;
    }

    public void setCuerpo(String foto){
        this.cuerpo = foto;
    }


    public String getCuerpo(){
        return cuerpo;
    }
    public void setUser(String foto){
        this.user = foto;
    }


    public String getUser(){
        return user;
    }

    public void setTag(String etiqueta){
        this.etiqueta = etiqueta;
    }


    public String getTag(){
        return etiqueta;
    }
}
