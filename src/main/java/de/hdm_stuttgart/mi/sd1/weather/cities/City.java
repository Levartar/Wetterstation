// Generated by http://www.jsonschema2pojo.org

package de.hdm_stuttgart.mi.sd1.weather.cities;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "country",
        "coord"
})
public class City {

  @JsonProperty("id")
  private int id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("country")
  private String country;
  //@JsonProperty("coord")
  @JsonIgnore
  private Coord coord;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * No args constructor for use in serialization
   */
  public City() {
  }

  /**
   * @param coord Stadtkoordinaten
   * @param id ID der Stadt
   * @param name Name der Stadt
   * @param country Land in dem sich die Stadt befindet.
   */
  public City(int id, String name, String country, Coord coord) {
    super();
    this.id = id;
    this.name = name;
    this.country = country;
    this.coord = coord;
  }

  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(int id) {
    this.id = id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  @JsonProperty("country")
  public void setCountry(String country) {
    this.country = country;
  }

//  @JsonProperty("coord")
//  public Coord getCoord() {
//    return coord;
//  }
//
//  @JsonProperty("coord")
//  public void setCoord(Coord coord) {
//    this.coord = coord;
//  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    return "City{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", country='" + country + '\'' +
            '}';
  }
}
