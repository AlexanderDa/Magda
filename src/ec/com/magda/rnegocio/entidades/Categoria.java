/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.rnegocio.entidades;

/**
 *
 * @author alexander
 */
public class Categoria {
    private int Id;
    private String Descripcion;

    public Categoria() {
    }

    public Categoria(int Id, String Descripcion) {
        this.Id = Id;
        this.Descripcion = Descripcion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public String toString() {
        return getDescripcion(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
