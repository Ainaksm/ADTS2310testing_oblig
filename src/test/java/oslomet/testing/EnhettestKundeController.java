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
    }
}
