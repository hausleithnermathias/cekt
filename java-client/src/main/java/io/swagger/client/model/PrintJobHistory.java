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
import org.threeten.bp.OffsetDateTime;

/**
 * A print job in the past.
 */
@ApiModel(description = "A print job in the past.")
public class PrintJobHistory {
  @SerializedName("referenceId")
  private String referenceId = null;

  @SerializedName("time_elapsed")
  private Double timeElapsed = null;

  @SerializedName("time_estimated")
  private Double timeEstimated = null;

  @SerializedName("time_total")
  private Double timeTotal = null;

  @SerializedName("datetime_started")
  private OffsetDateTime datetimeStarted = null;

  @SerializedName("datetime_finished")
  private OffsetDateTime datetimeFinished = null;

  @SerializedName("datetime_cleaned")
  private OffsetDateTime datetimeCleaned = null;

  /**
   * Gets or Sets result
   */
  @JsonAdapter(ResultEnum.Adapter.class)
  public enum ResultEnum {
    FINISHED("Finished"),
    
    ABORTED("Aborted");

    private String value;

    ResultEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ResultEnum fromValue(String text) {
      for (ResultEnum b : ResultEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<ResultEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ResultEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ResultEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return ResultEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("result")
  private ResultEnum result = null;

  @SerializedName("source")
  private String source = null;

  @SerializedName("reprint_original_uuid")
  private String reprintOriginalUuid = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("uuid")
  private String uuid = null;

  public PrintJobHistory timeElapsed(Double timeElapsed) {
    this.timeElapsed = timeElapsed;
    return this;
  }

   /**
   * Get timeElapsed
   * @return timeElapsed
  **/
  @ApiModelProperty(value = "")
  public Double getTimeElapsed() {
    return timeElapsed;
  }

  public void setTimeElapsed(Double timeElapsed) {
    this.timeElapsed = timeElapsed;
  }

  public PrintJobHistory timeEstimated(Double timeEstimated) {
    this.timeEstimated = timeEstimated;
    return this;
  }

   /**
   * Get timeEstimated
   * @return timeEstimated
  **/
  @ApiModelProperty(value = "")
  public Double getTimeEstimated() {
    return timeEstimated;
  }

  public void setTimeEstimated(Double timeEstimated) {
    this.timeEstimated = timeEstimated;
  }

  public PrintJobHistory timeTotal(Double timeTotal) {
    this.timeTotal = timeTotal;
    return this;
  }

   /**
   * Get timeTotal
   * @return timeTotal
  **/
  @ApiModelProperty(value = "")
  public Double getTimeTotal() {
    return timeTotal;
  }

  public void setTimeTotal(Double timeTotal) {
    this.timeTotal = timeTotal;
  }

  public PrintJobHistory datetimeStarted(OffsetDateTime datetimeStarted) {
    this.datetimeStarted = datetimeStarted;
    return this;
  }

   /**
   * Get datetimeStarted
   * @return datetimeStarted
  **/
  @ApiModelProperty(value = "")
  public OffsetDateTime getDatetimeStarted() {
    return datetimeStarted;
  }

  public void setDatetimeStarted(OffsetDateTime datetimeStarted) {
    this.datetimeStarted = datetimeStarted;
  }

  public PrintJobHistory datetimeFinished(OffsetDateTime datetimeFinished) {
    this.datetimeFinished = datetimeFinished;
    return this;
  }

   /**
   * Get datetimeFinished
   * @return datetimeFinished
  **/
  @ApiModelProperty(value = "")
  public OffsetDateTime getDatetimeFinished() {
    return datetimeFinished;
  }

  public void setDatetimeFinished(OffsetDateTime datetimeFinished) {
    this.datetimeFinished = datetimeFinished;
  }

  public PrintJobHistory datetimeCleaned(OffsetDateTime datetimeCleaned) {
    this.datetimeCleaned = datetimeCleaned;
    return this;
  }

   /**
   * Get datetimeCleaned
   * @return datetimeCleaned
  **/
  @ApiModelProperty(value = "")
  public OffsetDateTime getDatetimeCleaned() {
    return datetimeCleaned;
  }

  public void setDatetimeCleaned(OffsetDateTime datetimeCleaned) {
    this.datetimeCleaned = datetimeCleaned;
  }

  public PrintJobHistory result(ResultEnum result) {
    this.result = result;
    return this;
  }

   /**
   * Get result
   * @return result
  **/
  @ApiModelProperty(value = "")
  public ResultEnum getResult() {
    return result;
  }

  public void setResult(ResultEnum result) {
    this.result = result;
  }

  public PrintJobHistory source(String source) {
    this.source = source;
    return this;
  }

   /**
   * Get source
   * @return source
  **/
  @ApiModelProperty(value = "")
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public PrintJobHistory reprintOriginalUuid(String reprintOriginalUuid) {
    this.reprintOriginalUuid = reprintOriginalUuid;
    return this;
  }

   /**
   * Get reprintOriginalUuid
   * @return reprintOriginalUuid
  **/
  @ApiModelProperty(value = "")
  public String getReprintOriginalUuid() {
    return reprintOriginalUuid;
  }

  public void setReprintOriginalUuid(String reprintOriginalUuid) {
    this.reprintOriginalUuid = reprintOriginalUuid;
  }

  public PrintJobHistory name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PrintJobHistory uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

   /**
   * Get uuid
   * @return uuid
  **/
  @ApiModelProperty(value = "")
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   * Get uuid
   * @return referenceId
   **/
  @ApiModelProperty(value = "")
  public String getReferenceId() {return referenceId; }

  public void setReferenceId(String referenceId) {this.referenceId = referenceId; }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrintJobHistory printJobHistory = (PrintJobHistory) o;
    return Objects.equals(this.timeElapsed, printJobHistory.timeElapsed) &&
        Objects.equals(this.timeEstimated, printJobHistory.timeEstimated) &&
        Objects.equals(this.timeTotal, printJobHistory.timeTotal) &&
        Objects.equals(this.datetimeStarted, printJobHistory.datetimeStarted) &&
        Objects.equals(this.datetimeFinished, printJobHistory.datetimeFinished) &&
        Objects.equals(this.datetimeCleaned, printJobHistory.datetimeCleaned) &&
        Objects.equals(this.result, printJobHistory.result) &&
        Objects.equals(this.source, printJobHistory.source) &&
        Objects.equals(this.reprintOriginalUuid, printJobHistory.reprintOriginalUuid) &&
        Objects.equals(this.name, printJobHistory.name) &&
        Objects.equals(this.uuid, printJobHistory.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeElapsed, timeEstimated, timeTotal, datetimeStarted, datetimeFinished, datetimeCleaned, result, source, reprintOriginalUuid, name, uuid);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrintJobHistory {\n");
    
    sb.append("    timeElapsed: ").append(toIndentedString(timeElapsed)).append("\n");
    sb.append("    timeEstimated: ").append(toIndentedString(timeEstimated)).append("\n");
    sb.append("    timeTotal: ").append(toIndentedString(timeTotal)).append("\n");
    sb.append("    datetimeStarted: ").append(toIndentedString(datetimeStarted)).append("\n");
    sb.append("    datetimeFinished: ").append(toIndentedString(datetimeFinished)).append("\n");
    sb.append("    datetimeCleaned: ").append(toIndentedString(datetimeCleaned)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    reprintOriginalUuid: ").append(toIndentedString(reprintOriginalUuid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
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

