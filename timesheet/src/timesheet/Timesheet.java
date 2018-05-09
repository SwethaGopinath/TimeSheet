package timesheet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Timesheet {
	public static void main(String args[]) {
		int workingHour = 9;
		int noOfDays = 0;
		int noOfLeave = 0;
		String usrId, password,name="";
		boolean flag = false;
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("Swetha", "swetha"));
		employeeList.add(new Employee("Hima", "hima"));
		System.out.println("Enter the employee id and password");
		Scanner s = new Scanner(System.in);
		usrId = s.nextLine();
		password = s.nextLine();
		Employee e = new Employee(usrId, password);
		TimeSheetList temp = new TimeSheetList(e, "0", 0, 0, 0);

		ArrayList<TimeSheetList> timesheetlist = new ArrayList<TimeSheetList>();
		for (Employee emp : employeeList) {
			if (emp.getUsrId().equals(e.getUsrId()) && emp.getPassword().equals(e.getPassword())) {
				flag = true;
			}
		}
		if (flag == false) {
			System.out.println("Please enter the correct Employee-ID/Password");
		}
		if (flag == true) {
			System.out.println("Please fill the time_sheet");

			System.out.println("Enter the Date(DD-MM-YYYY) ");

			String date = s.nextLine();
			System.out.println("Working or leave");
			String working = s.nextLine();
			if (working.equalsIgnoreCase("working")) {
				System.out.println("please enter set of days :");
				noOfDays = s.nextInt();
				System.out.println("please enter working hours :");
				workingHour = s.nextInt();
			} else {
				System.out.println("please enter no of days on leave :");
				noOfLeave = s.nextInt();
				System.out.println("Enter the Employee Name to be searched :");
				Scanner scanner=new Scanner(System.in);
				 name = scanner.nextLine();
				
			}
			

			TimeSheetList TSL = new TimeSheetList(new Employee(usrId, password), date, noOfLeave, noOfDays,
					workingHour);
			TSL.setTotalLeaves((TSL.getTotalLeaves() + noOfLeave));
			TSL.setTotalHours(TSL.getTotalHours() + workingHour);
			timesheetlist.add(TSL);

			try {

				File file = new File("D:/TimeSheet.txt");
				String drive = "";

				// if file does'nt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);

				for (TimeSheetList t : timesheetlist) {
					bw.newLine();
					drive = "Today's time-sheet \t Name:\t" + t.getEmployee().getUsrId() +"\tDate:\t"+t.getdate() + "\tNumber of leaves taken:"
							+ "\t" + t.getNumberOfLeave() + "\tSet of days entered  \t" + t.getSetOfDays() + "\tTotal Hours: \t" + t.getTotalHours()
							+ "\t";
					
					if(t.getEmployee().getUsrId().equalsIgnoreCase(name)){
						bw.write("The effeciency of the employee " + t.getEmployee().getUsrId() + " is"
								+ t.getTotalHours() + " number of leaves taken is " + t.getTotalLeaves());
					
					}
					bw.write(drive);
					bw.newLine();
				}
				
				bw.newLine();
				bw.close();
				System.out.println("Done: " + drive);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

	}
}
