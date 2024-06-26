package ch04_pjt_01.ems;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import ch04_pjt_01.ems.member.Student;
import ch04_pjt_01.ems.member.service.EMSInformationService;
import ch04_pjt_01.ems.member.service.PrintStudentInformationService;
import ch04_pjt_01.ems.member.service.StudentDeleteService;
import ch04_pjt_01.ems.member.service.StudentModifyService;
import ch04_pjt_01.ems.member.service.StudentRegisterService;
import ch04_pjt_01.ems.member.service.StudentSelectService;
import ch04_pjt_01.ems.utils.InitSampleData;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		InitSampleData initSampleData = ctx.getBean("initSampleData", InitSampleData.class);
		String[] sNums = initSampleData.getsNums();
		String[] sIds = initSampleData.getsIds();
		String[] sPws = initSampleData.getsPws();
		String[] sNames = initSampleData.getsNames();
		int[] sAges = initSampleData.getsAges();
		char[] sGenders = initSampleData.getsGenders();
		String[] sMajors = initSampleData.getsMajors();
		
		StudentRegisterService registerService = ctx.getBean("studentRegisterService", StudentRegisterService.class);
		
		for(int i = 0; i< sNums.length; i++) {
			registerService.register(new Student(sNums[i], sIds[i], sPws[i], sNames[i], sAges[i], sGenders[i], sMajors[i]));
		}
		
		PrintStudentInformationService printStudentInformationService = ctx.getBean("printStudentInformationService", PrintStudentInformationService.class);
		printStudentInformationService.printStudentsInfo();
		
		
		registerService = ctx.getBean("studentRegisterService", StudentRegisterService.class);
		registerService.register(new Student("hbs006", "deer", "p0006", "melissa", 26, 'w', "Music"));
		
		printStudentInformationService.printStudentsInfo();
		
		StudentSelectService selectService = ctx.getBean("studentSelectService", StudentSelectService.class);
		Student selectedstudent = selectService.select("hbs006");
		
		System.out.println("STUDENT START ----------------");
		
		System.out.print("sNum: " + selectedstudent.getsNum() + "\t");
		System.out.print("| sId: " + selectedstudent.getsId() + "\t");
		System.out.print("| sPw: " + selectedstudent.getsPw() + "\t");
		System.out.print("| sName: " + selectedstudent.getsName() + "\t");
		System.out.print("| sAge: " + selectedstudent.getsAge() + "\t");
		System.out.print("| sGender: " + selectedstudent.getsGender() + "\t");
		System.out.println("| sMajor: " + selectedstudent.getsMajor() + "\t");
		System.out.println("END ---------------");
		
		StudentModifyService modifyService = ctx.getBean("studentModifyService", StudentModifyService.class);
		modifyService.modify(new Student("hbs006", "pig", "p0066", "melissa", 27, 'w', "Computer"));
		printStudentInformationService.printStudentsInfo();
		
		StudentDeleteService deleteService = ctx.getBean("studentDeleteService", StudentDeleteService.class);
		deleteService.delete("hbs005");
		printStudentInformationService.printStudentsInfo();
		
		EMSInformationService emsInfomationService = ctx.getBean("eMSInformationService", EMSInformationService.class);
		emsInfomationService.printEMSInformation();
		
		ctx.close();
	}

}
