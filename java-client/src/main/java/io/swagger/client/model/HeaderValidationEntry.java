/*
 * Ultimaker 3 API
 * REST api for the Ultimaker 3 - 3D printer.  Authentication: Any PUT/POST/DELETE api requires authentication before it can be used. Authentication is done with http digest (RFC 2617) without fallback to basic authentication.  To get a valid username/password combination, the following process can/should be followed.  1) POST /auth/request with 'application' and 'user' as parameters. The application name and user name will be shown to the user on the printer. The reply body will contain a json reply with an 'id' and 'key' part.  2) Repeatedly GET /auth/check/<id> until it reports 'authorized' or 'unauthorized'. This will be reported back once the end user selects if the application is allowed to use the API.  3) [optional] test the authentication, the earlier given 'id' is the username, the 'key' is the password. Use digest authentication on GET /auth/verify to test this.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * A validation result of the header check.
 */
@ApiModel(description = "A validation result of the header check.")
public class HeaderValidationEntry {
  /**
   * Gets or Sets faultCode
   */
  @JsonAdapter(FaultCodeEnum.Adapter.class)
  public enum FaultCodeEnum {
    HEADER_NOT_PRESENT("HEADER_NOT_PRESENT"),
    
    HEADER_MISSING_ITEM("HEADER_MISSING_ITEM"),
    
    MACHINE_TOO_SMALL_FOR_GCODE("MACHINE_TOO_SMALL_FOR_GCODE"),
    
    NOZZLE_AMOUNT_MISMATCH("NOZZLE_AMOUNT_MISMATCH"),
    
    NOZZLE_MISMATCH("NOZZLE_MISMATCH"),
    
    MATERIAL_NOT_LOADED("MATERIAL_NOT_LOADED");

    private String value;

    FaultCodeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static FaultCodeEnum fromValue(String text) {
      for (FaultCodeEnum b : FaultCodeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<FaultCodeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final FaultCodeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public FaultCodeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return FaultCodeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("fault_code")
  private FaultCodeEnum faultCode = null;

  /**
   * Gets or Sets faultLevel
   */
  @JsonAdapter(FaultLevelEnum.Adapter.class)
  public enum FaultLevelEnum {
    WARNING("WARNING"),
    
    ERROR("ERROR");

    private String value;

    FaultLevelEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static FaultLevelEnum fromValue(String text) {
      for (FaultLevelEnum b : FaultLevelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<FaultLevelEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final FaultLevelEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public FaultLevelEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return FaultLevelEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("fault_level")
  private FaultLevelEnum faultLevel = null;

  @SerializedName("message")
  private String message = null;

  @SerializedName("data")
  private String data = null;

  public HeaderValidationEntry faultCode(FaultCodeEnum faultCode) {
    this.faultCode = faultCode;
    return this;
  }

   /**
   * Get faultCode
   * @return faultCode
  **/
  @ApiModelProperty(value = "")
  public FaultCodeEnum getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(FaultCodeEnum faultCode) {
    this.faultCode = faultCode;
  }

  public HeaderValidationEntry faultLevel(FaultLevelEnum faultLevel) {
    this.faultLevel = faultLevel;
    return this;
  }

   /**
   * Get faultLevel
   * @return faultLevel
  **/
  @ApiModelProperty(value = "")
  public FaultLevelEnum getFaultLevel() {
    return faultLevel;
  }

  public void setFaultLevel(FaultLevelEnum faultLevel) {
    this.faultLevel = faultLevel;
  }

  public HeaderValidationEntry message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(value = "")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HeaderValidationEntry data(String data) {
    this.data = data;
    return this;
  }

   /**
   * This is a string encoded dictionary holding Key/Value pairs or an empty string
   * @return data
  **/
  @ApiModelProperty(value = "This is a string encoded dictionary holding Key/Value pairs or an empty string")
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HeaderValidationEntry headerValidationEntry = (HeaderValidationEntry) o;
    return Objects.equals(this.faultCode, headerValidationEntry.faultCode) &&
        Objects.equals(this.faultLevel, headerValidationEntry.faultLevel) &&
        Objects.equals(this.message, headerValidationEntry.message) &&
        Objects.equals(this.data, headerValidationEntry.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(faultCode, faultLevel, message, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HeaderValidationEntry {\n");
    
    sb.append("    faultCode: ").append(toIndentedString(faultCode)).append("\n");
    sb.append("    faultLevel: ").append(toIndentedString(faultLevel)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
