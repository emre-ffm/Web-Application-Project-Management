package edu.fra.uas.model.fileuploaddb;
//This will be used if i want to get a Message.Success or Fail
public class ResponseMessage {
	  private String message;
	  public ResponseMessage(String message) {
	    this.message = message;
	  }
	  public String getMessage() {
	    return message;
	  }
	  public void setMessage(String message) {
	    this.message = message;
	  }
	}