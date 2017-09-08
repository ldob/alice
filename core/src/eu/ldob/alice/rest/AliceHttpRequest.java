package eu.ldob.alice.rest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.*;

import eu.ldob.alice.mode.Mode;

public class AliceHttpRequest {

    private static AliceHttpRequest instance;

    private static final String BASE_URL = "http://www.ldob.eu/alice/";

    private AliceHttpRequest() {}

    public static AliceHttpRequest getInstance() {
        if(instance == null) {
            instance = new AliceHttpRequest();
        }

        return instance;
    }

    public void getHighscore(final AliceHttpListener listener) {
        HttpRequest http = new HttpRequest(HttpMethods.GET);
        http.setUrl(BASE_URL + "getHighscore.php");

        Gdx.net.sendHttpRequest(http, new HttpResponseListener() {

            @Override
            public void handleHttpResponse(HttpResponse httpResponse) {
                listener.onResult(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                listener.onError(t.getMessage());
            }

            @Override
            public void cancelled() {
                // do nothing
            }
        });
    }

    public void setScore(Mode mode, int score, final AliceHttpListener listener) {
        HttpRequest http = new HttpRequest(HttpMethods.GET);
        http.setUrl(BASE_URL + "setScore.php?mode=" + mode.toString() + "&player=Test&score=" + score);

        Gdx.net.sendHttpRequest(http, new HttpResponseListener() {

            @Override
            public void handleHttpResponse(HttpResponse httpResponse) {
                listener.onResult(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                listener.onError(t.getMessage());
            }

            @Override
            public void cancelled() {
                // do nothing
            }
        });
    }

}
