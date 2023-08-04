package com.vtalent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vtalent.Repository.EligibilityDetailsRepository;
import com.vtalent.entity.EligibiletyDetails;

@SpringBootApplication
public class Application implements ApplicationRunner {
	@Autowired
	private EligibilityDetailsRepository eligibilityDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("application started....");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		EligibiletyDetails e1 = new EligibiletyDetails();

		e1.setEligId(1);
		e1.setName("manikanta");
		e1.setMobile(9494013905l);
		e1.setGender('m');
		e1.setEmail("manikanta@gmail.com");
		e1.setSsn(5858568l);
		e1.setPlanName("SNAP");
		e1.setPlanStatus("Approved");
		eligibilityDetailsRepository.save(e1);

		EligibiletyDetails e2 = new EligibiletyDetails();

		e2.setEligId(2);
		e2.setName("yuva");
		e2.setMobile(9494013805l);
		e2.setGender('m');
		e2.setSsn(5856868l);
		e2.setEmail("yuva@gmail.com");
		e2.setPlanName("food");
		e2.setPlanStatus("Not Approved");
		eligibilityDetailsRepository.save(e2);

	}

}
