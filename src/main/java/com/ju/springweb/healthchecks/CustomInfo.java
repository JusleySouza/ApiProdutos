package com.ju.springweb.healthchecks;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;

public class CustomInfo implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("Description", "Project Description").build();

	}

}
