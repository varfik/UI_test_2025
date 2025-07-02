package tests;

import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = TestConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Config файл не найден!");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки config", e);
        }
    }

    public static String getPhone() {
        return props.getProperty("rutube.phone");
    }

    public static String getPassword() {
        return props.getProperty("rutube.password");
    }
}