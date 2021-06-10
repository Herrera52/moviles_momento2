package Modelos;

import java.io.Serializable;

public class usersModel implements Serializable {

    private String apart;
    private String pais;
    private String city;
    private String direccion;
    private String descrip;
    private String price;

    public usersModel(String apart, String pais, String city, String descrip, String direccion, String price){
        this.apart = apart;
        this.pais = pais;
        this.city = city;
        this.direccion = direccion;
        this.descrip = descrip;
        this.price = price;
    }

    public usersModel(){
        
    }

    public String getApart(){return apart;}

    public void setApart(String apart){this.apart = apart;}

    public String getPais(){return pais;}

    public void setPais(String pais){this.pais = pais;}

    public String getCity(){return city;}

    public void setCity(String city){this.city = city;}

    public String getDireccion(){return direccion;}

    public void setDireccion(String direccion){this.direccion = direccion;}

    public String getDescrip(){return descrip;}

    public void setDescrip(String descrip){this.descrip = descrip;}

    public String getPrice(){return price;}

    public void setPrice(String price){this.price = price;}
}
