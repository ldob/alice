package eu.ldob.alice.rest;

public interface AliceHttpListener {

    void onResult(String result);

    void onError(String errorMessage);

}
