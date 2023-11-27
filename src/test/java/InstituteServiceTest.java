import com.example.courseWork.models.Institute;
import com.example.courseWork.repo.InstituteRepo;
import com.example.courseWork.services.InstituteService;
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
public class InstituteServiceTest {

    @Mock
    private InstituteRepo instituteRepo;

    @InjectMocks
    private InstituteService instituteService;

    @Test
    public void testFindAll() {
        List<Institute> institutes = new ArrayList<>();
        institutes.add(new Institute());
        institutes.add(new Institute());

        Mockito.when(instituteRepo.findAll()).thenReturn(institutes);

        List<Institute> result = instituteService.findALl();

        Assert.assertEquals(institutes, result);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Institute institute = new Institute();
        institute.setId(id);

        Mockito.when(instituteRepo.findById(id)).thenReturn(Optional.of(institute));

        Institute result = instituteService.findById(id);

        Assert.assertEquals(institute, result);
    }

    @Test
    public void testFindByIdNotFound() {
        Integer id = 1;

        Mockito.when(instituteRepo.findById(id)).thenReturn(Optional.empty());

        Institute result = instituteService.findById(id);

        Assert.assertNull(result);
    }

    @Test
    public void testSave() {
        Institute institute = new Institute();

        instituteService.save(institute);

        Mockito.verify(instituteRepo).save(institute);
    }



    @Test
    public void testDelete() {
        Integer id = 1;

        instituteService.delete(id);

        Mockito.verify(instituteRepo).deleteById(id);
    }

    @Test
    public void testFindByName() {
        String name = "Test";
        Institute institute = new Institute();
        institute.setName(name);

        Mockito.when(instituteRepo.findByName(name)).thenReturn(Optional.of(institute));

        Institute result = instituteService.findByName(name);

        Assert.assertEquals(institute, result);
    }

    @Test
    public void testFindByNameNotFound() {
        String name = "Test";

        Mockito.when(instituteRepo.findByName(name)).thenReturn(Optional.empty());

        Institute result = instituteService.findByName(name);

        Assert.assertNull(result);
    }
}