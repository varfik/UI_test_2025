package tests;

import java.util.Optional;
public class TestConfig {
    public static String getPhone() {
        // Проверка системных свойств (-D параметры)
        String phone = System.getProperty("rutube.phone");
        if (phone != null) {
            return phone;
        }

        // Проверка переменных окружения
        phone = System.getenv("RUTUBE_PHONE");
        if (phone != null) {
            return phone;
        }
        throw new RuntimeException("Номер телефона не настроен! Введите: mvn test -Drutube.phone=YOUR_PHONE -Drutube.password=YOUR_PASSWORD");
    }

    public static String getPassword() {
        return Optional.ofNullable(System.getProperty("rutube.password"))
                .orElseThrow(() -> new RuntimeException("Пароль не настроен! Введите: mvn test -Drutube.phone=YOUR_PHONE -Drutube.password=YOUR_PASSWORD"));
    }
}
