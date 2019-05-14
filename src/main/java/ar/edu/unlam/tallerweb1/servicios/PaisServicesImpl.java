package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.PaisDao;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaisServicesImpl implements PaisServices {

    @Autowired
    private PaisDao paisDao;

    @Override
    @Transactional
    public List getAllPais() {
        return paisDao.getAllPais();
    }

    @Override
    @Transactional
    public Pais getPais(Long id) {
        return paisDao.getPais(id);
    }

    @Override
    @Transactional
    public void savePais(Pais pais) {
        paisDao.savePais(pais);
    }

    @Override
    @Transactional
    public void removePais(Long id) {
        paisDao.removePais(id);
    }

}
