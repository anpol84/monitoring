import com.example.courseWork.models.Cabinet;
import com.example.courseWork.repo.CabinetRepo;
import com.example.courseWork.services.CabinetService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CabinetServiceTest {

    @Mock
    private CabinetRepo cabinetRepo;

    @InjectMocks
    private CabinetService cabinetService;

    @Test
    public void testFindByNumber() {
        Integer number = 123;
        Cabinet cabinet = new Cabinet();
        cabinet.setNumber(number);

        Mockito.when(cabinetRepo.findByNumber(number)).thenReturn(Optional.of(cabinet));

        Cabinet result = cabinetService.findByNumber(number);

        Assert.assertEquals(cabinet, result);
    }

    @Test
    public void testFindByNumberNotFound() {
        Integer number = 123;

        Mockito.when(cabinetRepo.findByNumber(number)).thenReturn(Optional.empty());

        Cabinet result = cabinetService.findByNumber(number);

        Assert.assertNull(result);
    }

    @Test
    public void testFindAll() {
        List<Cabinet> cabinets = new ArrayList<>();
        cabinets.add(new Cabinet());
        cabinets.add(new Cabinet());

        Mockito.when(cabinetRepo.findAll()).thenReturn(cabinets);

        List<Cabinet> result = cabinetService.findAll();

        Assert.assertEquals(cabinets, result);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Cabinet cabinet = new Cabinet();
        cabinet.setId(id);

        Mockito.when(cabinetRepo.findById(id)).thenReturn(Optional.of(cabinet));

        Cabinet result = cabinetService.findById(id);

        Assert.assertEquals(cabinet, result);
    }

    @Test
    public void testFindByIdNotFound() {
        Integer id = 1;

        Mockito.when(cabinetRepo.findById(id)).thenReturn(Optional.empty());

        Cabinet result = cabinetService.findById(id);

        Assert.assertNull(result);
    }

    @Test
    public void testSave() {
        Cabinet cabinet = new Cabinet();

        cabinetService.save(cabinet);

        Mockito.verify(cabinetRepo).save(cabinet);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Cabinet cabinet = new Cabinet();
        cabinet.setId(id);
        cabinet.setNumber(123);

        Cabinet cabinet1 = new Cabinet();
        cabinet1.setId(id);

        Mockito.when(cabinetRepo.findById(id)).thenReturn(Optional.of(cabinet1));

        cabinetService.update(cabinet, id);

        Mockito.verify(cabinetRepo).save(cabinet1);
        Assert.assertEquals(cabinet.getNumber(), cabinet1.getNumber());
    }

    @Test
    public void testDelete() {
        Integer id = 1;

        cabinetService.delete(id);

        Mockito.verify(cabinetRepo).deleteById(id);
    }
}