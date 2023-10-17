package api.item;

import Environment.Environment;
import Environment.EnvironmentItem;

public class ApiItem implements EnvironmentItem {
    private static final String CONFIG_KEY = "api";
    private static final ApiItem INSTANCE = new ApiItem();
    private ApiItem(){
        //singleton
    }

    public static ApiItem instance() {
        return INSTANCE;
    }

    public <T> T getConfig(Class<T> cls) {
        return Environment.instance().get(CONFIG_KEY, cls);
    }

}
