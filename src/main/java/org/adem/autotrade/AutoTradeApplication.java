package org.adem.autotrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AutoTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoTradeApplication.class, args);
	}

}
