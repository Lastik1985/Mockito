import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiselmplTest {
    GeoServiceImpl geoService;

    @BeforeEach
    public void start() {
        geoService = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("ByIp")
    public void testByIp(String ip, Location expectedLocation) {
        Location actualLocation = geoService.byIp(ip);
        assertEquals(expectedLocation.getCountry(), actualLocation.getCountry());
    }

    public static Stream<Arguments> ByIp() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"172."})
    void testByIpRu(String ip) {

        Country expected = Country.RUSSIA;
        Location actual = geoService.byIp(ip);

        Assertions.assertEquals(expected, actual.getCountry());
    }
}
