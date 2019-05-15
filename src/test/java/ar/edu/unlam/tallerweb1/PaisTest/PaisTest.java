package ar.edu.unlam.tallerweb1.PaisTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Continente;

import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
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
        england.setNombre("England");
        england.setIdioma("ingles");
        session.save(england);

        Pais argentina = new Pais();
        argentina.setNombre("Argentina");
        argentina.setIdioma("español");
        session.save(argentina);

        Criteria paises = getSession().createCriteria(Pais.class)
                            .add(Restrictions.eq("idioma", "ingles"));

        List<Pais> results = paises.list();
        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback
    public void verPaisesDeEuropa () {
        Session session = getSession();

        Continente europa = new Continente();
        europa.setNombre("europa");
        session.save(europa);

        Continente america = new Continente();
        america.setNombre("america");
        session.save(america);

        Pais england = new Pais();
        england.setNombre("England");
        england.setIdioma("ingles");
        england.setContinente(europa);
        session.save(england);

        Pais paraguay = new Pais();
        paraguay.setNombre("Paraguay");
        paraguay.setIdioma("guarani");
        paraguay.setContinente(america);
        session.save(paraguay);

        Criteria paises = getSession().createCriteria(Pais.class, "p")
                            .createAlias("continente", "c")
                            .add(Restrictions.eq("c.nombre", "europa"));

        List<Pais> results = paises.list();
        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback
    public void verPaisesCuyaCapitalEsteAlNorteTropicoCancer () {
        Session session = getSession();

        Ubicacion ubicacionBerlin = new Ubicacion();
        ubicacionBerlin.setLatitud(25.266666666);
        ubicacionBerlin.setLongitud(30.14);
        session.save(ubicacionBerlin);

        Ubicacion ubicacionBuenosAires = new Ubicacion();
        ubicacionBuenosAires.setLatitud(15.456336666);
        ubicacionBuenosAires.setLongitud(30.14);
        session.save(ubicacionBuenosAires);

        Continente europa = new Continente();
        europa.setNombre("europa");
        session.save(europa);

        Continente america = new Continente();
        america.setNombre("america");
        session.save(america);

        Pais alemania = new Pais();
        alemania.setNombre("Alemania");
        alemania.setIdioma("aleman");
        alemania.setContinente(europa);
        session.save(alemania);

        Pais argentina = new Pais();
        argentina.setNombre("Argentina");
        argentina.setIdioma("español");
        argentina.setContinente(america);
        session.save(argentina);

        Ciudad berlin = new Ciudad();
        berlin.setNombre("Berlin");
        berlin.setPais(alemania);
        berlin.setUbicacion(ubicacionBerlin);
        session.save(berlin);

        Ciudad buenosAires = new Ciudad();
        buenosAires.setNombre("Buenos Aires");
        buenosAires.setPais(alemania);
        buenosAires.setUbicacion(ubicacionBuenosAires);
        session.save(buenosAires);

        alemania.setCapital(berlin);
        argentina.setCapital(buenosAires);

        Criteria paisCriteria = session.createCriteria(Pais.class,"p")
                                .createAlias("capital", "c")
                                .createAlias("c.ubicacion", "u")
                                .add(Restrictions.gt("u.latitud", 23.26666666));

        List<Pais> results = paisCriteria.list();
        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback
    public void todasLasCiudadesDelHemisferioSur () {
        Session session = getSession();

        Ubicacion ubicacionBuenosAires = new Ubicacion();
        ubicacionBuenosAires.setLatitud(-25.26);
        ubicacionBuenosAires.setLongitud(40.14);
        session.save(ubicacionBuenosAires);

        Ubicacion ubicacionRoma = new Ubicacion();
        ubicacionRoma.setLatitud(77.13);
        ubicacionRoma.setLongitud(12.44);
        session.save(ubicacionRoma);

        Continente america = new Continente();
        america.setNombre("america");
        session.save(america);

        Continente europa = new Continente();
        europa.setNombre("europa");
        session.save(europa);

        Ciudad buenosAires = new Ciudad();
        buenosAires.setNombre("Buenos Aires");
        buenosAires.setUbicacion(ubicacionBuenosAires);
        session.save(buenosAires);

        Ciudad roma = new Ciudad();
        roma.setNombre("Roma");
        roma.setUbicacion(ubicacionRoma);
        session.save(roma);

        Pais argentina = new Pais();
        argentina.setNombre("Argentina");
        argentina.setIdioma("español");
        argentina.setContinente(america);
        argentina.setCapital(buenosAires);
        session.save(argentina);

        Pais italia = new Pais();
        italia.setNombre("Italia");
        italia.setIdioma("italiano");
        italia.setContinente(america);
        italia.setCapital(buenosAires);
        session.save(italia);

        buenosAires.setPais(argentina);
        roma.setPais(italia);

        Criteria ciudadCriteria = session.createCriteria(Ciudad.class,"c")
                                  .createAlias("c.ubicacion","u")
                                  .add(Restrictions.lt("u.latitud", 0.00));

        List<Ciudad> results = ciudadCriteria.list();
        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);
    }
}