import com.example.courseWork.models.UntreatedStudent;
import com.example.courseWork.models.User;
import com.example.courseWork.repo.UntreatedStudentRepo;
import com.example.courseWork.services.UntreatedStudentService;
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
public class UntreatedStudentServiceTest {

    @Mock
    private UntreatedStudentRepo untreatedStudentRepo;

    @InjectMocks
    private UntreatedStudentService untreatedStudentService;

    @Test
    public void testFindAll() {
        List<UntreatedStudent> untreatedStudents = new ArrayList<>();
        untreatedStudents.add(new UntreatedStudent());
        untreatedStudents.add(new UntreatedStudent());

        Mockito.when(untreatedStudentRepo.findAll()).thenReturn(untreatedStudents);

        List<UntreatedStudent> result = untreatedStudentService.findAll();

        Assert.assertEquals(untreatedStudents, result);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        UntreatedStudent untreatedStudent = new UntreatedStudent();
        untreatedStudent.setId(id);

        Mockito.when(untreatedStudentRepo.findById(id)).thenReturn(Optional.of(untreatedStudent));

        UntreatedStudent result = untreatedStudentService.findById(id);

        Assert.assertEquals(untreatedStudent, result);
    }

    @Test
    public void testSave() {
        UntreatedStudent untreatedStudent = new UntreatedStudent();

        untreatedStudentService.save(untreatedStudent);

        Mockito.verify(untreatedStudentRepo).save(untreatedStudent);
    }

    @Test
    public void testDelete() {
        Integer id = 1;

        untreatedStudentService.delete(id);

        Mockito.verify(untreatedStudentRepo).deleteById(id);
    }

    @Test
    public void testFindByUser() {
        User user = new User();
        user.setId(1);

        UntreatedStudent untreatedStudent = new UntreatedStudent();
        untreatedStudent.setUser(user);

        Mockito.when(untreatedStudentRepo.findByUser(user)).thenReturn(Optional.of(untreatedStudent));

        Object result = untreatedStudentService.findByUser(user);

        Assert.assertEquals(untreatedStudent, result);
    }
}