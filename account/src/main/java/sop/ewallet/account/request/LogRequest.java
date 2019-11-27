package sop.ewallet.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogRequest {

  @JsonProperty("source_id")
  private long sourceId;

  public LogRequest(long sourceId) {
    this.sourceId = sourceId;
  }

  public long getSourceId() {
    return sourceId;
  }

  public void setSourceId(long sourceId) {
    this.sourceId = sourceId;
  }
}
