package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
@Table(name="Ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Ubicacion ubicacion;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais () {
        return pais;
    }

    public void setPais (Pais pais) {
        this.pais = pais;
    }

    public Ubicacion getUbicacion () {
        return ubicacion;
    }

    public void setUbicacion (Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
