package at.itproject.core;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.PrintJobHistory;

public class Message {

    @SerializedName("referenceId")
    private String referenceId = null;

    @SerializedName("printjobHistory")
    private PrintJobHistory printJobHistory;

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
    public PrintJobHistory getPrintJobHistory() {return printJobHistory; }

    public void setPrintJobHistory(PrintJobHistory message) {this.printJobHistory = message; }
}
