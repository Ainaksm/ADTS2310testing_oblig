package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhettestKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController kundeController;

    @Mock
    // denne skal mock-es
    private AdminRepository repository;

    @Mock
    // denne skal mock-es
    private Sikkerhet sjekk;

    @Test
    public void hentAlle_loggetInn() {
        // arrange
        List<Kunde> kunder = new ArrayList<>();
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        Kunde annenKunde = new Kunde("09090098765",
                "Geir", "Sol", "Spikkestadveien 22", "3430",
                "Spikkestad", "66663333", "JaJaJa");
        kunder.add(enKunde);
        kunder.add(annenKunde);

        when(sjekk.loggetInn()).thenReturn(enKunde.getPersonnummer(),annenKunde.getPersonnummer());
        when(repository.hentAlleKunder()).thenReturn(kunder);

        // act
        List<Kunde> resultat = kundeController.hentAlle();

        // assert
        assertEquals(kunder, resultat);
    }

    @Test
    public void hentAlle_ikkeInnlogget() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Kunde> resultat = kundeController.hentAlle();

        // assert
        assertNull(resultat);
    }

    @Test
    public void lagreKunde_loggetInn() {
        // arrange
        Kunde enKunde = new Kunde("09090098765",
                "Geir", "Sol", "Spikkestadveien 22", "3430",
                "Spikkestad", "66663333", "JaJaJa");

        when(sjekk.loggetInn()).thenReturn(enKunde.getPersonnummer());
        when(repository.registrerKunde(any(Kunde.class))).thenReturn("Logget inn");

        // act
        String resultat = kundeController.lagreKunde(enKunde);

        // assert
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void lagreKunde_ikkeLoggetInn() {
        // arrange
        Kunde enKunde = new Kunde("09090098765",
                "Geir", "Sol", "Spikkestadveien 22", "3430",
                "Spikkestad", "66663333", "JaJaJa");

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = kundeController.lagreKunde(enKunde);

        // assert
        assertEquals("Ikke logget inn", resultat);
    }
}
