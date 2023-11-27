import com.example.courseWork.models.Course;
import com.example.courseWork.repo.CourseRepo;
import com.example.courseWork.services.CourseService;
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
public class CourseServiceTest {

    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void testFindAll() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());
        courses.add(new Course());

        Mockito.when(courseRepo.findAll()).thenReturn(courses);

        List<Course> result = courseService.findAll();

        Assert.assertEquals(courses, result);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Course course = new Course();
        course.setId(id);

        Mockito.when(courseRepo.findById(id)).thenReturn(Optional.of(course));

        Course result = courseService.findById(id);

        Assert.assertEquals(course, result);
    }

    @Test
    public void testFindByIdNotFound() {
        Integer id = 1;

        Mockito.when(courseRepo.findById(id)).thenReturn(Optional.empty());

        Course result = courseService.findById(id);

        Assert.assertNull(result);
    }

    @Test
    public void testSave() {
        Course course = new Course();

        courseService.save(course);

        Mockito.verify(courseRepo).save(course);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Course course = new Course();
        course.setId(id);
        course.setName("Test");
        course.setNumber(123);
        course.setSemester(1);

        Course course1 = new Course();
        course1.setId(id);

        Mockito.when(courseRepo.findById(id)).thenReturn(Optional.of(course1));

        courseService.update(course, id);

        Mockito.verify(courseRepo).save(course1);
        Assert.assertEquals(course.getName(), course1.getName());
        Assert.assertEquals(course.getNumber(), course1.getNumber());
        Assert.assertEquals(course.getSemester(), course1.getSemester());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        courseService.deleteById(id);

        Mockito.verify(courseRepo).deleteById(id);
    }

    @Test
    public void testFindByNameAndNumberAndSemester() {
        String name = "Test";
        Integer number = 123;
        Integer semester = 1;
        Course course = new Course();
        course.setName(name);
        course.setNumber(number);
        course.setSemester(semester);

        Mockito.when(courseRepo.findByNameAndNumberAndSemester(name, number, semester)).thenReturn(Optional.of(course));

        Course result = courseService.findByNameAndNumberAndSemester(name, number, semester);

        Assert.assertEquals(course, result);
    }

    @Test
    public void testFindByNameAndNumberAndSemesterNotFound() {
        String name = "Test";
        Integer number = 123;
        Integer semester = 1;

        Mockito.when(courseRepo.findByNameAndNumberAndSemester(name, number, semester)).thenReturn(Optional.empty());

        Course result = courseService.findByNameAndNumberAndSemester(name, number, semester);

        Assert.assertNull(result);
    }
}