package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestKontoController {

    @InjectMocks
    // denne skal testes
    private AdminKontoController kontoController;

    @Mock
    // denne skal Mock-es
    private AdminRepository repository;

    @Mock
    // denne skal mock-es
    private Sikkerhet sjekk;

    @Test
    public void hentAlleKonti_LoggetInn() {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        konti.add(konto1);

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.hentAlleKonti()).thenReturn(konti);

        // act
        List<Konto> resultat = kontoController.hentAlleKonti();

        // assert
        assertEquals(konti, resultat);

    }

    @Test
    public void hentAlleKonti_ikkeLoggetInn() {
        // arrage
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = kontoController.hentAlleKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void registrerKonto_LoggetInn() {
        //arrange
    }

    @Test
    public void registrerKonto_ikkeLoggetInn() {
        // arrange
    }
}
