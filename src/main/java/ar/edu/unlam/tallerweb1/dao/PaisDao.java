package ar.edu.unlam.tallerweb1.dao;
import  ar.edu.unlam.tallerweb1.modelo.Pais;
import java.util.List;

public interface PaisDao {
    List getAllPais();
    Pais getPais(Long id);
    void savePais(Pais pais);
    void removePais(Long id);
}
