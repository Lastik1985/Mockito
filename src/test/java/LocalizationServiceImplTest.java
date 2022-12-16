import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import org.junit.jupiter.params.ParameterizedTest;
import ru.netology.i18n.LocalizationServiceImpl;
import static ru.netology.entity.Country.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocalizationServiceImplTest {
    LocalizationServiceImpl LocalizationService;

    @BeforeEach
    public void start () {

        LocalizationService = new LocalizationServiceImpl();
    }
    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"RUSSIA", "USA", "BRAZIL"})
    void localeTest(Country country) {
        String expected = "Welcome";
        String actual = LocalizationService.locale(country);

        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @MethodSource("greeting")
    public void testLocale(Country country, String expectedWelcoming) {
        assertEquals(LocalizationService.locale(country), expectedWelcoming);

    }

    public static Stream<Arguments> greeting() {
        String ruWelcome = "Добро пожаловать";
        String enWelcome = "Welcome";
        return Stream.of(Arguments.of(RUSSIA, ruWelcome),
                Arguments.of(GERMANY, enWelcome),
                Arguments.of(USA, enWelcome),
                Arguments.of(BRAZIL, enWelcome));
    }


}
