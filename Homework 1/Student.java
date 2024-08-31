import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentInterface,java.io.Serializable{
	
	private transient Scanner sc = new Scanner(System.in).useDelimiter("[,\n]");
	private String CourseRegistered;
	private static final long serialVersionUID = 1L;
	
	public Student(){
	}
	public Student(String uname,String pass,String first,String last){
		super(uname,pass,first,last);	
		this.CourseRegistered="null";
	}
	@Override
	public void viewAllCourse(ArrayList<Course>courseList) {
		for(Course c:courseList){
			c.printViewCourse();
		}
	}	
	@Override
	public void viewNotFull(ArrayList<Course>courseList) {
		for(Course c:courseList){
			if(c.getCurrentStuNum()!=c.getMaxStu()){
				c.printViewCourse();
			}
		}	
	}
	@Override
	public void register(ArrayList<Course> courseList, ArrayList<Student> studentList) {
		System.out.println("Please enter the course name and section number, use comma in between");
		Scanner sca = new Scanner(System.in).useDelimiter("[,\n]");

		String registerCourseName = sca.next();
		int registerSectionNum = sca.nextInt();

		if (this.CourseRegistered == null) {
			for (Course register : courseList) {
				String courseName = register.getCourseName().replaceAll("\\s+", "").toLowerCase();
				String inputCourseName = registerCourseName.replaceAll("\\s+", "").toLowerCase();

				if (courseName.equals(inputCourseName) && register.getSectionNum() == registerSectionNum) {
					System.out.println("Please enter your full name with comma in between");
					String first = sca.next();
					String last = sca.next();

					register.registerStu((Student) this, first, last, studentList);

					this.CourseRegistered = registerCourseName;

					System.out.println("You successfully registered " + register.getCourseName());
					break;
				}
			}
		} else {
			StringBuilder sb = new StringBuilder(this.CourseRegistered);
			for (Course register : courseList) {
				String courseName = register.getCourseName().replaceAll("\\s+", "").toLowerCase();
				String inputCourseName = registerCourseName.replaceAll("\\s+", "").toLowerCase();

				if (courseName.equals(inputCourseName) && register.getSectionNum() == registerSectionNum) {
					System.out.println("Please enter your full name with comma in between");
					String first = sca.next();
					String last = sca.next();

					register.registerStu((Student) this, first, last, studentList);

					sb.append(register.getCourseName());
					sb.append(",");
					this.CourseRegistered = sb.toString();

					System.out.println("You successfully registered " + register.getCourseName());
					break;
				} else if (courseList.indexOf(register) == (courseList.size() - 1)) {
					System.out.println("Cannot find the course");
				}
			}
		}
	}

	@Override
	public void withdraw(ArrayList<Course> courseList,ArrayList<Student>studentList) {
		Scanner withdrawC=new Scanner(this.CourseRegistered).useDelimiter("[,\n]");
		 Scanner sc=new Scanner(System.in).useDelimiter("[,\n]");
		StringBuilder sb=new StringBuilder();
		
		System.out.println("Please enter your name, use comma in between");
		String first=sc.next();
		String last=sc.next();
		for(Student s:studentList){
			if(s.getFirstname().equals(first)){
				if(s.getLastname().equals(last)){
					System.out.println("Please enter the course name to withdraw");
					String withdrawCname=sc.next();
					
					while(withdrawC.hasNext()){
						if(!((withdrawC.next()).equals(withdrawCname))){
							sb.append(withdrawC.next());
							sb.append(",");
						}
						this.CourseRegistered=sb.toString();
					}
					
					for(Course withdraw:courseList){
						if((withdraw.getCourseName()).equals(withdrawCname)){
							withdraw.withdrawStu(s);
							break;
						}
					}
							
							System.out.println("You successfully withdraw "+withdrawCname);
							break;
							
						}
					}
				}
		}
				
	@Override
	public void viewAllRegistered() {
		if(!((this.CourseRegistered).equals("null"))){
			System.out.println(this.CourseRegistered);
		}else{
			System.out.println("No course registered");
		}
	}
}