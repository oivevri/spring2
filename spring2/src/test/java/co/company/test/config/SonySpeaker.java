package co.company.test.config;

import org.springframework.stereotype.Component;

@Component
public class SonySpeaker implements Speaker {

	@Override
	public void volumeup() {
		System.out.println("SonySpeaker 볼륨 업");
	}
}
