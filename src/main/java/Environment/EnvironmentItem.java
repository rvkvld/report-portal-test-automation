package Environment;

public interface EnvironmentItem {
    <T> T getConfig(Class<T> cls);
}
