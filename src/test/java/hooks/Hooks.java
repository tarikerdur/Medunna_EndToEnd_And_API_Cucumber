package hooks;

import io.cucumber.java.Before;

import static base_urls.MedunnaBaseUrl.setUp;

public class Hooks {
    @Before("@create_room") // sadece bu tag'den çalışsın demiş olduk. bir sey yazmazsak tum run'larda calisir.
    public void beforeApi() {
        setUp();
    }
}
