package at.itproject.core;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

public class PrintJobTimout {

    @SerializedName("referenceId")
    private String referenceId = null;

    @SerializedName("errorMessage")
    private String message = "Printer is not reachable anymore. Container killed !";

    /**
     * Get uuid
     * @return referenceId
     **/
    @ApiModelProperty(value = "")
    public String getReferenceId() {return referenceId; }

    public void setReferenceId(String referenceId) {this.referenceId = referenceId; }

    /**
     * Get uuid
     * @return message
     **/
    @ApiModelProperty(value = "")
    public String getMessage() {return message; }

    public void setMessage(String message) {this.message = message; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PrintJobTimeout {\n");

        sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
        sb.append("    message: ").append(toIndentedString(referenceId)).append("\n");
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
