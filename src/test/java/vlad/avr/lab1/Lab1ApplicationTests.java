package vlad.avr.lab1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vlad.avr.lab1.util.PowHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class Lab1ApplicationTests {

	@Test
	void testPowHelper() {
		assertEquals(6, PowHelper.pow(3,2));
	}

}
