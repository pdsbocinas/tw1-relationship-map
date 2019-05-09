package ar.edu.unlam.tallerweb1.PaisTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Continente;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Assert;
import java.util.List;

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
                .like("idioma","Mou%",MatchMode.ANYWHERE))
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
        europa.setId((long) 1);
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
    public void verPaises
}