package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import java.util.List;

public interface PaisServices {
    List getAllPais();
    Pais getPais(int id);
    void savePais(Pais pais);
    void removePais(int id);
}
