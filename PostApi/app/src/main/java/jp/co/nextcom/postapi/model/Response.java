package jp.co.nextcom.postapi.model;

public class Response {
    private Data data;
    private String result;
    private String message;

    public Response(Data data, String result, String message) {
        this.data = data;
        this.result = result;
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

