package eight.integration;

import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class SampleTests {
	@Test
	public void test() {
		int testCount = 1000;

		long startTime = System.currentTimeMillis();
		IntStream.range(0, testCount).parallel().forEach(i -> {
//			new RestTemplate().getForEntity("http://localhost:8080/?mode=leastConnection", String.class);
			new RestTemplate().getForEntity("http://localhost:8080/?mode=roundRobin", String.class);
		});

		long endTime = System.currentTimeMillis();

		System.out.println(testCount / ((endTime - startTime) / 1000));
	}
}
