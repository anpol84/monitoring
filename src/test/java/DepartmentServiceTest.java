import com.example.courseWork.models.Department;
import com.example.courseWork.models.Institute;
import com.example.courseWork.repo.DepartmentRepo;
import com.example.courseWork.services.DepartmentService;
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
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepo departmentRepo;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    public void testFindAll() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department());
        departments.add(new Department());

        Mockito.when(departmentRepo.findAll()).thenReturn(departments);

        List<Department> result = departmentService.findAll();

        Assert.assertEquals(departments, result);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Department department = new Department();
        department.setId(id);

        Mockito.when(departmentRepo.findById(id)).thenReturn(Optional.of(department));

        Department result = departmentService.findById(id);

        Assert.assertEquals(department, result);
    }

    @Test
    public void testFindByIdNotFound() {
        Integer id = 1;

        Mockito.when(departmentRepo.findById(id)).thenReturn(Optional.empty());

        Department result = departmentService.findById(id);

        Assert.assertNull(result);
    }

    @Test
    public void testFindByName() {
        String name = "Test";
        Department department = new Department();
        department.setName(name);

        Mockito.when(departmentRepo.findByName(name)).thenReturn(Optional.of(department));

        Department result = departmentService.findByName(name);

        Assert.assertEquals(department, result);
    }

    @Test
    public void testFindByNameNotFound() {
        String name = "Test";

        Mockito.when(departmentRepo.findByName(name)).thenReturn(Optional.empty());

        Department result = departmentService.findByName(name);

        Assert.assertNull(result);
    }

    @Test
    public void testSave() {
        Department department = new Department();

        departmentService.save(department);

        Mockito.verify(departmentRepo).save(department);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Department department = new Department();
        department.setId(id);
        department.setName("Test");
        department.setInstitute(new Institute());
        department.setTeachers(new ArrayList<>());

        Department department1 = new Department();
        department1.setId(id);

        Mockito.when(departmentRepo.findById(id)).thenReturn(Optional.of(department1));

        departmentService.update(department, id);

        Mockito.verify(departmentRepo).save(department1);
        Assert.assertEquals(department.getName(), department1.getName());
        Assert.assertEquals(department.getInstitute(), department1.getInstitute());
        Assert.assertEquals(department.getTeachers(), department1.getTeachers());
    }

    @Test
    public void testDelete() {
        Integer id = 1;

        departmentService.delete(id);

        Mockito.verify(departmentRepo).deleteById(id);
    }
}