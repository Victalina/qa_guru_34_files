package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Person {

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;
  private Integer age;

  private PersonAddress address;

  @JsonProperty("phone_numbers")
  private List<PersonPhoneNumbers> phoneNumbers;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getAge() {
    return age;
  }

  public PersonAddress getAddress() {
    return address;
  }

  public List<PersonPhoneNumbers> getPhoneNumbers() {
    return phoneNumbers;
  }
}
