import com.example.courseWork.models.*;
import com.example.courseWork.repo.EventRepo;
import com.example.courseWork.repo.UserRepo;
import com.example.courseWork.services.RegistrationService;
import com.example.courseWork.services.UntreatedStudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UntreatedStudentService untreatedStudentService;
    @Mock
    private EventRepo eventRepo;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    public void testRegister() {
        User user = new User();
        user.setRole("ROLE_STUDENT");

        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        registrationService.register(user);

        Mockito.verify(userRepo).save(user);
        Mockito.verify(untreatedStudentService).save(Mockito.any(UntreatedStudent.class));
    }

    @Test
    public void testRegisterAdmin() {
        User user = new User();

        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        registrationService.registerAdmin(user);

        Mockito.verify(userRepo).save(user);
        Assert.assertEquals("ROLE_ADMIN", user.getRole());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        User user = new User();
        user.setId(id);

        Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(user));

        User result = registrationService.findById(id);

        Assert.assertEquals(user, result);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);

        Mockito.when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));

        User result = registrationService.findByEmail(email);

        Assert.assertEquals(user, result);
    }

    @Test
    public void testFindByLogin() {
        String login = "test";
        User user = new User();
        user.setLogin(login);

        Mockito.when(userRepo.findByLogin(login)).thenReturn(Optional.of(user));

        User result = registrationService.findByLogin(login);

        Assert.assertEquals(user, result);
    }

    @Test
    public void testFindTeachers() {
        List<User> teachers = new ArrayList<>();
        teachers.add(new User());
        teachers.add(new User());

        Mockito.when(userRepo.findAllByRole("ROLE_TEACHER")).thenReturn(teachers);

        List<User> result = registrationService.findTeachers();

        Assert.assertEquals(teachers, result);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        User userToUpdate = new User();
        userToUpdate.setId(id);
        userToUpdate.setLogin("test");
        userToUpdate.setPassword("password");
        userToUpdate.setEmail("test@test.com");
        userToUpdate.setName("Test");
        userToUpdate.setSurname("User");
        userToUpdate.setLastname("Lastname");


        User existingUser = new User();
        existingUser.setId(id);

        Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(existingUser));
        Mockito.when(passwordEncoder.encode(userToUpdate.getPassword())).thenReturn("encodedPassword");

        registrationService.update(userToUpdate, id);

        Mockito.verify(userRepo).save(existingUser);
    }


    @Test
    public void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        Mockito.when(userRepo.findAll()).thenReturn(users);

        List<User> result = registrationService.findAll();

        Assert.assertEquals(users, result);
    }



    @Test
    public void testSignEvent() {
        Integer userId = 1;
        Integer eventId = 2;
        User user = new User();
        user.setId(userId);
        Event event = new Event();
        event.setId(eventId);
        event.setCount(1);

        SignEventDTO signEventDTO = new SignEventDTO();
        signEventDTO.setUserId(userId);
        signEventDTO.setEventId(eventId);

        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(eventRepo.findById(eventId)).thenReturn(Optional.of(event));

        registrationService.signEvent(signEventDTO);

        Mockito.verify(userRepo).save(user);
        Mockito.verify(eventRepo).save(event);
    }



    @Test
    public void testSave() {
        User user = new User();

        registrationService.save(user);

        Mockito.verify(userRepo).save(user);
    }
}