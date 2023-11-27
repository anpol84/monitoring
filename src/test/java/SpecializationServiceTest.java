import com.example.courseWork.models.Course;
import com.example.courseWork.models.Specialization;
import com.example.courseWork.repo.SpecializationRepo;
import com.example.courseWork.services.SpecializationService;
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
public class SpecializationServiceTest {

    @Mock
    private SpecializationRepo specializationRepo;

    @InjectMocks
    private SpecializationService specializationService;

    @Test
    public void testFindById() {
        Integer id = 1;
        Specialization specialization = new Specialization();
        specialization.setId(id);

        Mockito.when(specializationRepo.findById(id)).thenReturn(Optional.of(specialization));

        Specialization result = specializationService.findById(id);

        Assert.assertEquals(specialization, result);
    }

    @Test
    public void testFindByName() {
        String name = "Specialization";
        Specialization specialization = new Specialization();
        specialization.setName(name);

        Mockito.when(specializationRepo.findByName(name)).thenReturn(Optional.of(specialization));

        Specialization result = specializationService.findByName(name);

        Assert.assertEquals(specialization, result);
    }

    @Test
    public void testFindByCode() {
        String code = "SPC";
        Specialization specialization = new Specialization();
        specialization.setCode(code);

        Mockito.when(specializationRepo.findByCode(code)).thenReturn(Optional.of(specialization));

        Specialization result = specializationService.findByCode(code);

        Assert.assertEquals(specialization, result);
    }

    @Test
    public void testFindAll() {
        List<Specialization> specializations = new ArrayList<>();
        specializations.add(new Specialization());
        specializations.add(new Specialization());

        Mockito.when(specializationRepo.findAll()).thenReturn(specializations);

        List<Specialization> result = specializationService.findAll();

        Assert.assertEquals(specializations, result);
    }

    @Test
    public void testSave() {
        Specialization specialization = new Specialization();

        specializationService.save(specialization);

        Mockito.verify(specializationRepo).save(specialization);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Specialization specializationToUpdate = new Specialization();
        specializationToUpdate.setId(id);
        specializationToUpdate.setName("New Specialization");

        Specialization existingSpecialization = new Specialization();
        existingSpecialization.setId(id);

        Mockito.when(specializationRepo.findById(id)).thenReturn(Optional.of(existingSpecialization));

        specializationService.update(specializationToUpdate, id);

        Mockito.verify(specializationRepo).save(existingSpecialization);
    }

    @Test
    public void testDelete() {
        Integer id = 1;

        specializationService.delete(id);

        Mockito.verify(specializationRepo).deleteById(id);
    }

    @Test
    public void testFindCoursesBySpecializationId() {
        Integer id = 1;
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());
        courses.add(new Course());

        Specialization specialization = new Specialization();
        specialization.setId(id);
        specialization.setCourses(courses);

        Mockito.when(specializationRepo.findById(id)).thenReturn(Optional.of(specialization));

        List<Course> result = specializationService.findCoursesBySpecializationId(id);

        Assert.assertEquals(courses, result);
    }
}
