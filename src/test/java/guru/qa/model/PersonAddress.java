package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonAddress {

  @JsonProperty("street_address")
  private String streetAddress;
  private String city;
  private String state;
  @JsonProperty("postal_code")
  private String postalCode;

  public String getStreetAddress() {
    return streetAddress;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }
}
