import com.example.courseWork.models.*;
import com.example.courseWork.repo.EventRepo;
import com.example.courseWork.services.EventService;
import com.example.courseWork.services.PastRetakeService;
import com.example.courseWork.services.RegistrationService;
import com.example.courseWork.services.RetakeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private EventRepo eventRepo;



    @InjectMocks
    private EventService eventService;

    @Test
    public void testFindAll() {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());

        Mockito.when(eventRepo.findAll()).thenReturn(events);

        List<Event> result = eventService.findAll();


        Assert.assertEquals(events, result);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Event event = new Event();
        event.setId(id);

        Mockito.when(eventRepo.findById(id)).thenReturn(Optional.of(event));

        Event result = eventService.findById(id);

        Assert.assertEquals(event, result);
    }

    @Test
    public void testFindByIdNotFound() {
        Integer id = 1;

        Mockito.when(eventRepo.findById(id)).thenReturn(Optional.empty());

        Event result = eventService.findById(id);

        Assert.assertNull(result);
    }

    @Test
    public void testFindByDateAndNumberAndCabinet() {
        Date date = new Date();
        Integer number = 1;
        Cabinet cabinet = new Cabinet();

        Event event = new Event();

        Mockito.when(eventRepo.findByDateAndNumberAndCabinet(date, number, cabinet)).thenReturn(Optional.of(event));

        Event result = eventService.findByDateAndNumberAndCabinet(date, number, cabinet);

        Assert.assertEquals(event, result);
    }

    @Test
    public void testFindByDateAndNumberAndCabinetNotFound() {
        Date date = new Date();
        Integer number = 1;
        Cabinet cabinet = new Cabinet();

        Mockito.when(eventRepo.findByDateAndNumberAndCabinet(date, number, cabinet)).thenReturn(Optional.empty());

        Event result = eventService.findByDateAndNumberAndCabinet(date, number, cabinet);

        Assert.assertNull(result);
    }

    @Test
    public void testSave() {
        Event event = new Event();

        eventService.save(event);

        Mockito.verify(eventRepo).save(event);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Event event = new Event();
        event.setId(id);
        event.setDate(new Date());
        event.setCabinet(new Cabinet());
        event.setNumber(1);
        event.setCourse(new Course());
        event.setCount(10);
        event.setTeacher(new User());

        Event event1 = new Event();
        event1.setId(id);

        Mockito.when(eventRepo.findById(id)).thenReturn(Optional.of(event1));

        eventService.update(event, id);

        Mockito.verify(eventRepo).save(event1);
        Assert.assertEquals(event.getDate(), event1.getDate());
        Assert.assertEquals(event.getCabinet(), event1.getCabinet());
        Assert.assertEquals(event.getNumber(), event1.getNumber());
        Assert.assertEquals(event.getCourse(), event1.getCourse());
        Assert.assertEquals(event.getCount(), event1.getCount());
        Assert.assertEquals(event.getTeacher(), event1.getTeacher());
    }

    @Test
    public void testDelete() {
        Integer id = 1;

        eventService.delete(id);

        Mockito.verify(eventRepo).deleteById(id);
    }



}