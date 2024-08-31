import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User implements AdminInterface,Cloneable{
	
	Scanner s=new Scanner(System.in);
	Scanner sc=new Scanner(System.in).useDelimiter("[,\n]");
	private static final long serialVersionUID = 1L;
	
	public Admin(){
		
	}
	public Admin(String uname,String pass,String first,String last){
		super(uname,pass,first,last);
	}
	
	@Override
	//Current enrollment number should be 0 and no list of names of student in this course
	public Course createCourse() {
		System.out.println("Please enter course name, courseID, maximum enrollment number,"
				+ "instructor, section number and location, please use commas in between");
		Course c=new Course(sc.next(),sc.next(),sc.next(),sc.next(),sc.next(),sc.next());
		System.out.println("Created a new course, name is "+c.getCourseName());
		return c;	
		}
	
	@Override
	public void deleteCourse(ArrayList<Course> courseList) {
		System.out.println("Please enter the course ID and section number "
				+ "that you would like to delete");
		
		String deleteID=sc.next();
		int sectionNum=sc.nextInt();
		
		for(Course deletec:courseList){
			if(deleteID.equals(deletec.getCourseID())&&(sectionNum==(deletec.getSectionNum()))){
				int deleteIndex=courseList.indexOf(deletec);
				courseList.remove(deleteIndex);
				System.out.println(deletec.getCourseName()+" is deleted");
				break;
			}else if (courseList.indexOf(deletec) == courseList.size()-1){
				System.out.println("There is no such course");
			}
		}
	}
	
	@Override
	public void editCourse(ArrayList<Course> courseList) {
		System.out.println("Please enter the course ID and section number "
				+ "that you would like to edit");
		String editcID=null;int editSectionNum=0;

			editcID=sc.next();
			System.out.println(editcID);
			editSectionNum=sc.nextInt();
			System.out.println(editSectionNum);

		int editIndex=0;
		for(Course editc:courseList){
			if(editcID.equals(editc.getCourseID())){
				if(editSectionNum==(editc.getSectionNum())){
				editIndex=courseList.indexOf(editc);
				break;
				}
			}
		}
		Course editc=courseList.get(editIndex);
		System.out.println("Please enter the information you would like to edit");
		String edit=s.nextLine();
		switch(edit){
		case "Course name":
				System.out.println("Please enter the new course name");
				editc.setCourseName(s.nextLine());
				break;
		case "Course ID":
				System.out.println("Please enter the new course ID");
				editc.setCourseID(s.nextLine());
				break;
		case "Maximum Enrollment Number":
				System.out.println("Please enter the new maximum enrollment number");
				editc.setMaxStu(s.nextInt());
				break;
				
		/*Can't change list of name and current enrollment number since this is based on registration*/
				
		case "Instructor":
				System.out.println("Please enter the new instructor");
				editc.setInstructor(s.nextLine());
				break;
		case "Course Section":
				System.out.println("Please enter the new course section");
				editc.setSectionNum(s.nextInt());
				break;
		case "Location":
				System.out.println("Please enter the new location");
				editc.setLocation(s.nextLine());
				break;
		}
		courseList.set(editIndex, editc);		
	}
	
	@Override
	public void display(ArrayList<Course>courseList) {
		System.out.println("Please enter the courseID and the section number,use comma in between");
		String displaycID=sc.next();
		int sectionNum=sc.nextInt();
		Course displayC = new Course();
		for(Course displayc:courseList){
			if(((displaycID).equals(displayc.getCourseID()))&&(sectionNum==(displayc.getSectionNum()))){
				displayc.print();
				displayC=displayc;
				break;
			}
		}
	}
	
	@Override
	public void registerStudent(ArrayList<Student>studentList) {
		System.out.println("Please enter the student's username, password, "
				+ "first name and last name, and please use commas in between");
		Student adminRegisterS=new Student(sc.next(),sc.next(),sc.next(),sc.next());
		studentList.add(adminRegisterS);
		System.out.println(adminRegisterS.getUsername()+" is successfully registered");
	}
	
	@Override
	public void viewCourse(ArrayList<Course>courseList) {
		for(int i=0;i<courseList.size();i++){
			courseList.get(i).printViewCourse();
		}
	}
	
	@Override
	public void viewAllFull(ArrayList<Course> courseList) {
		boolean foundFullCourse = false;
		for (Course viewFullc : courseList) {
			if (viewFullc.getCurrentStuNum() == viewFullc.getMaxStu()) {
				viewFullc.printViewCourse(); // Assuming this method prints the course details
				foundFullCourse = true;
				break; // If you only want to print the first full course, otherwise remove the break to print all full courses
			}
		}
		if (!foundFullCourse) {
			System.out.println("No full classes");
		}
	}
	
	@Override
	public void writeToFile(ArrayList<Course> courseList) {
		String fileName = "Full Courses.txt";
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(Course c:courseList){
				if(c.getCurrentStuNum()==c.getMaxStu()){
					bufferedWriter.write(c.getCourseName()+"/t");
					bufferedWriter.write(c.getCourseID()+"/t");
					bufferedWriter.write(c.getCurrentStuNum()+"/t");
					bufferedWriter.write(c.getMaxStu()+"/t");
					bufferedWriter.write(c.getListOfNames()+"/t");
					bufferedWriter.write(c.getInstructor()+"/t");
					bufferedWriter.write(c.getLocation()+"/t");
				}
			}
			bufferedWriter.close();
			System.out.println("Finished writing all full courses to file");
		}
		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}
	
	@Override
	public void viewStudentName(ArrayList<Course> courseList) {
		while (true) { // Infinite loop that we will break out of when successful
			try {
				System.out.println("Please enter the course name and the section number, use comma in between:");
				String Cname = sc.next(); // Retrieve the course name
				int CsecNum = sc.nextInt(); // Retrieve the section number
				sc.nextLine(); // Consume the rest of the line including newline

				boolean found = false;
				for (Course viewC : courseList) {
					if (Cname.equalsIgnoreCase(viewC.getCourseName().trim()) && (CsecNum == viewC.getSectionNum())) {
						String listOfNames = viewC.getListOfNames();
						if (listOfNames == null || listOfNames.isEmpty()) {
							System.out.println("No students are registered for this course.");
						} else {
							System.out.println(listOfNames);
						}
						found = true;
						break;
					}
				}

				if (!found) {
					System.out.println("Incorrect course name or section number, please try again!");
				} else {
					break; // Exit the while-loop because we found the course and printed the names
				}
			} catch (InputMismatchException ime) {
				System.out.println("You entered invalid data. Please try again!");
				sc.next(); // Consume the problematic input
			}
		}
	}


	@Override
	public void aStudentRegistered(ArrayList<Student>studentList) {
		System.out.println("Please enter student's first name and last name, use comma in between");
		String firstname=sc.next();String lastname=sc.next();
		for(Student studentRegisC:studentList){
			if((firstname.equals(studentRegisC.getFirstname()))
					&&(lastname.equals(studentRegisC.getLastname()))){
				studentRegisC.viewAllRegistered();
				break;
			}
		}	
	}
	
	public void sortCourse(ArrayList<Course>courseList){
		for(int i=0;i<courseList.size()-1;i++){
			for(int j=i+1;j<courseList.size();j++){
				if((courseList.get(i).getCurrentStuNum())<(courseList.get(j).getCurrentStuNum())){
					Course smallerDuplicate=(Course) courseList.get(i).clone();
					courseList.set(i, courseList.get(j));
					courseList.set(j, smallerDuplicate);
				}
			}
		}
		System.out.println("Sorting Completed");
	}
}
