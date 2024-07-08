package com.example.surprise.API;

public class Producto {
    private int idproducto;
    private String producto;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getPreciou() {
        return preciou;
    }

    public void setPreciou(int preciou) {
        this.preciou = preciou;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String categoria;
    private int preciou;
    private String descripcion;

    public Producto(int idproducto, String producto, String categoria, int preciou, String descripcion, String imagen, int stock) {
        this.idproducto = idproducto;
        this.producto = producto;
        this.categoria = categoria;
        this.preciou = preciou;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.stock = stock;
    }

    private String imagen;
    private int stock;

    // Constructor, getters y setters
    // ...
}