import com.example.courseWork.models.User;
import com.example.courseWork.repo.UserRepo;
import com.example.courseWork.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    public void testLoadUserByUsername() {
        String username = "testUser";
        User user = new User();
        user.setLogin(username);

        Mockito.when(userRepo.findByLogin(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(username);

        Assert.assertEquals(user.getLogin(), userDetails.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {
        String username = "testUser";

        Mockito.when(userRepo.findByLogin(username)).thenReturn(Optional.empty());

        userService.loadUserByUsername(username);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);

        Mockito.when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail(email);

        Assert.assertEquals(user, result.get());
    }

    @Test
    public void testFindByEmailNotFound() {
        String email = "test@test.com";

        Mockito.when(userRepo.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail(email);

        Assert.assertFalse(result.isPresent());
    }
}