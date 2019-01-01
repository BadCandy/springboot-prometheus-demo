package me.christ9979.prometheusdemo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RestController
public class PrometheusDemoApplication {

	@Autowired
	private MeterRegistry meterRegistry;

	private Counter counter;

	@PostConstruct
	public void init() {
		counter = meterRegistry.counter("api.call.count");
	}

	public static void main(String[] args) {
		SpringApplication.run(PrometheusDemoApplication.class, args);
	}

	@GetMapping("/test")
	public String test() {
		counter.increment();
		return "test";
	}
}

