import java.util.ArrayList;

public interface StudentInterface {
	//Needed revised for Courses readings, suppose to return courses; 
	public void viewAllCourse(ArrayList<Course> courseList);
	public void viewNotFull(ArrayList<Course> courseList);
	public void register(ArrayList<Course>courseList,ArrayList<Student>studentList);
	public void withdraw(ArrayList<Course>courseList,ArrayList<Student>studentList);
	public void viewAllRegistered();
}
