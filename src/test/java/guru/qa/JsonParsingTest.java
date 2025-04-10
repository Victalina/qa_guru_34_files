package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

public class JsonParsingTest {

  private ClassLoader cl = FileParsingTest.class.getClassLoader();
  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void jsonParsingWithJacksonTest() throws Exception {

    try (Reader reader = new InputStreamReader(
            cl.getResourceAsStream("person.json"))) {

      Person actual = objectMapper.readValue(reader, Person.class);

      Assertions.assertEquals("Mike", actual.getFirstName());
      Assertions.assertEquals("Smith", actual.getLastName());
      Assertions.assertEquals(27, actual.getAge());
      Assertions.assertEquals("21 2nd Street", actual.getAddress().getStreetAddress());
      Assertions.assertEquals("New York", actual.getAddress().getCity());
      Assertions.assertEquals("NY", actual.getAddress().getState());
      Assertions.assertEquals("10021", actual.getAddress().getPostalCode());
      Assertions.assertEquals("home", actual.getPhoneNumbers().get(0).getType());
      Assertions.assertEquals("212 555-1234", actual.getPhoneNumbers().get(0).getNumber());
      Assertions.assertEquals("mobile", actual.getPhoneNumbers().get(1).getType());
      Assertions.assertEquals("646 555-4567", actual.getPhoneNumbers().get(1).getNumber());
    }
  }
}
