package sop.ewallet.transactions.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class LogRequest {

  @NotNull
  @JsonProperty("source_id")
  private long sourceId;

  public LogRequest() {
  }

  public long getSourceId() {
    return sourceId;
  }

  public void setSourceId(long sourceId) {
    this.sourceId = sourceId;
  }
}
