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
        "lon",
        "lat"
})
public class Coord {

  @JsonProperty("lon")
  private double lon;
  @JsonProperty("lat")
  private double lat;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * No args constructor for use in serialization
   */
  public Coord() {
  }

  /**
   * @param lon Längengrad
   * @param lat Breitengrad
   */
  public Coord(double lon, double lat) {
    super();
    this.lon = lon;
    this.lat = lat;
  }

  @JsonProperty("lon")
  public double getLon() {
    return lon;
  }

  @JsonProperty("lon")
  public void setLon(double lon) {
    this.lon = lon;
  }

  @JsonProperty("lat")
  public double getLat() {
    return lat;
  }

  @JsonProperty("lat")
  public void setLat(double lat) {
    this.lat = lat;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
