import com.example.courseWork.models.*;
import com.example.courseWork.repo.CourseRepo;
import com.example.courseWork.repo.RetakeRepo;
import com.example.courseWork.services.RetakeService;
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
public class RetakeServiceTest {

    @Mock
    private RetakeRepo retakeRepo;
    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private RetakeService retakeService;

    @Test
    public void testSet() {
        RetakeDTO retakeDTO = new RetakeDTO();
        retakeDTO.setUser(new User());
        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        retakeDTO.setCourses(courses);
        List<Integer> attempts = new ArrayList<>();
        attempts.add(2);
        retakeDTO.setAttempts(attempts);

        Course course = new Course();
        course.setId(1);

        Mockito.when(courseRepo.findById(1)).thenReturn(Optional.of(course));

        retakeService.set(retakeDTO);

        Mockito.verify(retakeRepo).save(Mockito.any(Retake.class));
    }

    @Test
    public void testFindAll() {
        List<Retake> retakes = new ArrayList<>();
        retakes.add(new Retake());
        retakes.add(new Retake());

        Mockito.when(retakeRepo.findAll()).thenReturn(retakes);

        List<Retake> result = retakeService.findAll();

        Assert.assertEquals(retakes, result);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Retake retake = new Retake();
        retake.setId(id);

        Mockito.when(retakeRepo.findById(id)).thenReturn(Optional.of(retake));

        Retake result = retakeService.findById(id);

        Assert.assertEquals(retake, result);
    }

    @Test
    public void testCreate() {
        Retake retake = new Retake();

        retakeService.create(retake);

        Mockito.verify(retakeRepo).save(retake);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Retake retakeToUpdate = new Retake();
        retakeToUpdate.setId(id);
        retakeToUpdate.setAttempt(2);

        Retake existingRetake = new Retake();
        existingRetake.setId(id);

        Mockito.when(retakeRepo.findById(id)).thenReturn(Optional.of(existingRetake));

        retakeService.update(retakeToUpdate, id);

        Mockito.verify(retakeRepo).save(existingRetake);
    }

    @Test
    public void testDelete() {
        Integer id = 1;
        Retake retakeToDelete = new Retake();
        retakeToDelete.setId(id);

        retakeService.delete(id);

        Mockito.verify(retakeRepo).deleteById(id);
    }



    @Test
    public void testFindByUserAndCourse() {
        User user = new User();
        Course course = new Course();

        retakeService.findByUserAndCourse(user, course);

        Mockito.verify(retakeRepo).findByUserAndCourse(user, course);
    }
}