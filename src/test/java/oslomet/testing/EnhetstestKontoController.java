package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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
        Konto enKonto = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        konti.add(enKonto);

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
        Konto enKonto = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(enKonto.getPersonnummer());
        when(repository.registrerKonto(any(Konto.class))).thenReturn("Logget inn");

        // act
        String resultat = kontoController.registrerKonto(enKonto);

        // assert
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void registrerKonto_ikkeLoggetInn() {
        // arrange
        Konto enKonto = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = kontoController.registrerKonto(enKonto);

        // arange
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void endreKonto_loggetInn() {
        // arrange
        Konto enKonto = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(enKonto.getPersonnummer());
        when(repository.endreKonto(any(Konto.class))).thenReturn("Logget inn");

        // act
        String resultat = kontoController.endreKonto(enKonto);

        // asssert
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void endreKonto_ikkeInnlogget() {
        // arrange
        Konto enKonto = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = kontoController.endreKonto(enKonto);

        // assert
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void slettKonto_loggetInn() {
        // arrange
        Konto enKonto = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(enKonto.getPersonnummer());
        when(repository.slettKonto(enKonto.getKontonummer())).thenReturn("Logget inn");

        // act
        String resultat = kontoController.slettKonto(enKonto.getKontonummer());

        // assert
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void slettKonto_ikkeLoggetInn() {
        // arrange
        Konto enKonto = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = kontoController.slettKonto(enKonto.getKontonummer());

        // assert
        assertEquals("Ikke innlogget", resultat);
    }
}
