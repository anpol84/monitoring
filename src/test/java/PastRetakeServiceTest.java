import com.example.courseWork.models.PastRetake;
import com.example.courseWork.models.User;
import com.example.courseWork.repo.PastRetakeRepo;
import com.example.courseWork.services.PastRetakeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PastRetakeServiceTest {

    @Mock
    private PastRetakeRepo pastRetakeRepo;

    @InjectMocks
    private PastRetakeService pastRetakeService;

    @Test
    public void testFindAllByUser() {
        User user = new User();
        List<PastRetake> pastRetakes = new ArrayList<>();
        pastRetakes.add(new PastRetake());
        pastRetakes.add(new PastRetake());

        Mockito.when(pastRetakeRepo.findAllByUser(user)).thenReturn(pastRetakes);

        List<PastRetake> result = pastRetakeService.findAllByUser(user);

        Assert.assertEquals(pastRetakes, result);
    }

    @Test
    public void testSave() {
        PastRetake pastRetake = new PastRetake();

        pastRetakeService.save(pastRetake);

        Mockito.verify(pastRetakeRepo).save(pastRetake);
    }

    @Test
    public void testDelete() {
        Integer id = 1;

        pastRetakeService.delete(id);

        Mockito.verify(pastRetakeRepo).deleteById(id);
    }
}