package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@TestConfiguration
	public static class FooConfig {
//		Uncomment to make the test pass.
//
//        @Bean
//        public MapSessionRepository mapSessionRepository() {
//            return new MapSessionRepository(new ConcurrentHashMap<>());
//        }
	}

	@Test
	void contextLoads() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("foo", "bar");
		mockMvc.perform(MockMvcRequestBuilders.get("/").session(session))
			.andExpect(status().isOk())
			.andExpect(content().string("bar"));
	}
}
