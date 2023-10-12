package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ulstu.is.sbapp.speaker.service.SpeakerService;

@SpringBootTest
class SbappApplicationTests {

	@Autowired
	SpeakerService speakerService;

	@Test
	void testSpeakerRus() {
		final String res = speakerService.say("Мир", "ru");
		Assertions.assertEquals("Привет Мир!", res);
	}

	@Test
	void testSpeakerEng() {
		final String res = speakerService.say("World", "en");
		Assertions.assertEquals("Hello World!", res);
	}

	@Test
	void testSpeakerDeu() {
		final String res = speakerService.say("Welt", "de");
		Assertions.assertEquals("Hallo Welt!", res);
	}

	@Test
	void testSpeakerErrorWired() {
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> speakerService.say("Мир", "rus"));
	}

	@Test
	void testUp(){
		final String res = speakerService.sayUp("Мир","ru");
		Assertions.assertEquals("ПРИВЕТ МИР!", res);
	}
	@Test
	void testLow() {
		final String res = speakerService.sayLow("Мир", "ru");
		Assertions.assertEquals("привет мир!", res);
	}
}
