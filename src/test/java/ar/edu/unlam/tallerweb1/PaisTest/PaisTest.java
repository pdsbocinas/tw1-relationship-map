package ar.edu.unlam.tallerweb1.PaisTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Continente;

import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Assert;
import java.util.List;
import org.hibernate.Criteria;
import static org.assertj.core.api.Assertions.*;

public class PaisTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void verPaisesDeHablaInglesa () {
        Session session = getSession();

        Pais england = new Pais();
        england.setId(1);
        england.setNombre("England");
        england.setIdioma("ingles");
        session.save(england);

        Pais argentina = new Pais();
        argentina.setId(2);
        argentina.setNombre("Argentina");
        argentina.setIdioma("español");
        session.save(argentina);

        // aplicar criteria eq
        List results;
        results = session.createCriteria(Pais.class)
                .add(Restrictions
                .like("idioma","ing%",MatchMode.ANYWHERE))
                .list();

        // y depsues hacer el assert
        assertThat(results).isNotNull();

    }

    @Test
    @Transactional
    @Rollback
    public void verPaisesDeEuropa () {
        Session session = getSession();

        Continente europa = new Continente();
        europa.setId(1);
        europa.setNombre("europa");
        session.save(europa);

        Pais england = new Pais();
        england.setId(1);
        england.setNombre("England");
        england.setIdioma("ingles");
        england.setContinente(europa);
        session.save(england);

        Assert.assertEquals("europa", england.getContinente().getNombre());
    }

    @Test
    @Transactional
    @Rollback
    public void verPaisesCuyaCapitalEsteAlNorteTropicoCancer () {
        Session session = getSession();

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId(1);
        ubicacion.setLatitud(252614);
        ubicacion.setLongitud(301456);
        session.save(ubicacion);

        Continente europa = new Continente();
        europa.setId(1);
        europa.setNombre("europa");
        session.save(europa);

        Pais alemania = new Pais();
        alemania.setId(1);
        alemania.setNombre("Alemania");
        alemania.setIdioma("aleman");
        alemania.setContinente(europa);
        session.save(alemania);

        Ciudad berlin = new Ciudad();
        berlin.setId(1);
        berlin.setNombre("Berlin");
        berlin.setPais(alemania);
        berlin.setUbicacion(ubicacion);
        session.save(berlin);

        Criteria paisCriteria = session.createCriteria(Pais.class,"p")
                                .createCriteria("capital", "c")
                                .createCriteria("ubicacion", "u")
                                .add(Restrictions.gt("u.latitud", 232614));

        List<Pais> results = paisCriteria.list();
        assertThat(results).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void todasLasCiudadesDelHemisferioSur () {
        Session session = getSession();

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId(1);
        ubicacion.setLatitud(-252614);
        ubicacion.setLongitud(401456);
        session.save(ubicacion);

        Continente america = new Continente();
        america.setId(1);
        america.setNombre("america");
        session.save(america);

        Pais argentina = new Pais();
        argentina.setId(1);
        argentina.setNombre("Argentina");
        argentina.setIdioma("español");
        argentina.setContinente(america);
        session.save(argentina);

        Ciudad buenosAires = new Ciudad();
        buenosAires.setId(1);
        buenosAires.setNombre("Buenos Aires");
        buenosAires.setPais(argentina);
        buenosAires.setUbicacion(ubicacion);
        session.save(buenosAires);

        // 5- Hacer con junit un test que busque todas las ciudades del hemisferio sur
        Criteria ciudadCriteria = session.createCriteria(Ciudad.class,"c")
                                  .createCriteria("ubicacion","u")
                                  .add(Restrictions.lt("u.latitud", 0));

        List<Ciudad> results = ciudadCriteria.list();
        assertThat(results).isNotNull();
    }
}