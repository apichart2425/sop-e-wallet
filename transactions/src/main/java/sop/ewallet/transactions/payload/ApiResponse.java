package sop.ewallet.transactions.payload;

public class ApiResponse<T> {
  private boolean success;
  private T payload;

  public ApiResponse() {
  }

  public ApiResponse(boolean success, T payload) {
    this.success = success;
    this.payload = payload;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public T getPayload() {
    return payload;
  }

  public void setPayload(T payload) {
    this.payload = payload;
  }
}
