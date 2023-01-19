package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
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
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentTransaksjoner_loggetInn() {
        // arrange
        Konto enKonto = new Konto();

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(enKonto);

        // act
        Konto resultat = bankController.hentTransaksjoner("","","");

        // assert
        assertEquals(enKonto, resultat);
    }

    @Test
    public void hentTransaksjoner_ikkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        Konto resultat = bankController.hentTransaksjoner(null,null,null);

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKundeInfo_loggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentSaldi_LoggetInn() {
        // arrange
        List<Konto> saldi = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                        720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        saldi.add(konto1);
        saldi.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentSaldi(anyString())).thenReturn(saldi);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertEquals(saldi, resultat);
    }

    @Test
    public void hendtSaldi_IkkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertNull(resultat);
    }

    @Test
    public void registrerBetaling_loggetInn() {
        // arrange
        Transaksjon enTransaksjon = new Transaksjon(1,"01010110523",
                100,"09.01.23","slpeis","","12345678901");

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.registrerBetaling(enTransaksjon)).thenReturn("OK");

        //act
        String resultat = bankController.registrerBetaling(enTransaksjon);

        // assert
        assertEquals("OK", resultat);
    }

    @Test
    public void registrerBetaling_ikkeLoggetInn() {
        // arrange
        Transaksjon enTransaksjon = new Transaksjon(1,"01010110523",
                100,"09.01.23","slpeis","","12345678901");

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = bankController.registrerBetaling(enTransaksjon);

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentBetalinger_LoggetInn() {
        // arrange
        List<Transaksjon> betaling = new ArrayList<>();
        Transaksjon transaksjon1 = new Transaksjon(1,"01010110523",
                100,"09.01.23","slpeis","","12345678901");
        Transaksjon transaksjon2 = new Transaksjon(2,"12345678901",
                50,"22.01.23","gave","","01010110523");
        betaling.add(transaksjon1);
        betaling.add(transaksjon2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentBetalinger(anyString())).thenReturn(betaling);

        // act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        //assert
        assertEquals(betaling,resultat);

    }

    @Test
    public void hentBetalinger_IkkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        // assert
        assertNull(resultat);
    }

    @Test
    public void utforBetaling_loggetInn() {
        // arrange
        List<Transaksjon> enBetaling = new ArrayList<>();
        Transaksjon betaling1 = new Transaksjon(1,"01010110523",
                100,"09.01.23","slpeis","","12345678901");
        enBetaling.add(betaling1);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.utforBetaling(betaling1.getTxID())).thenReturn("OK");
        when(repository.hentBetalinger(anyString())).thenReturn(enBetaling);

        // act
        List<Transaksjon> resultat = bankController.utforBetaling(betaling1.getTxID());

        // assert
        assertEquals(enBetaling, resultat);
    }

    @Test
    public void utforBetaling_ikkeLoggetInn() {
        // arrange
        Transaksjon betaling1 = new Transaksjon(1,"01010110523",
                100,"09.01.23","slpeis","","12345678901");

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> resultat = bankController.utforBetaling(betaling1.getTxID());

        // assert
        assertNull(resultat);
    }

    @Test
    public void endre_loggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        // act
        String resultat = bankController.endre(enKunde);

        // assert
        assertEquals("OK", resultat);
    }

    @Test
    public void endre_ikkeLoggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = bankController.endre(enKunde);

        // assert
        assertNull(resultat);
    }
}

