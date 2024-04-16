package ch05_pjt_01_Auto.contact;

import org.springframework.context.support.GenericXmlApplicationContext;

import ch05_pjt_01_Auto.contact.service.ContactRegisterService;
import ch05_pjt_01_Auto.contact.service.ContactSearchService;
import ch05_pjt_01_Auto.contact.utils.InitSampleData;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		
		InitSampleData initSampleData = ctx.getBean("initSampleData", InitSampleData.class);
		String[] names = initSampleData.getNames();
		String[] phoneNumbers = initSampleData.getPhoneNumbers();
		
		ContactRegisterService registerService = ctx.getBean("registerService", ContactRegisterService.class);
		for(int i = 0; i < names.length; i++) {
			ContactSet contactSet = new ContactSet(names[i], phoneNumbers[i]);
			registerService.register(contactSet);
		}
		
		ContactSearchService searchService = ctx.getBean("searchService", ContactSearchService.class);
		
		ContactSet contactSet = searchService.searchContact("류현진");
		System.out.println("name: " + contactSet.getName());
		System.out.println("phone number: " + contactSet.getPhoneNumber());
		System.out.println("-------------------------");
		
		contactSet = searchService.searchContact("손흥민");
		System.out.println("name: " + contactSet.getName());
		System.out.println("phone number: " + contactSet.getPhoneNumber());
		System.out.println("-------------------------");

		contactSet = searchService.searchContact("김연경");
		System.out.println("name: " + contactSet.getName());
		System.out.println("phone number: " + contactSet.getPhoneNumber());
		System.out.println("-------------------------");
	}

}
