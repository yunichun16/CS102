import java.util.ArrayList;

public interface AdminInterface {
//Course Management//	
	public Course createCourse();
	public void deleteCourse(ArrayList<Course>list);
	public void editCourse(ArrayList<Course>list);
	public void display(ArrayList<Course>list);
	public void registerStudent(ArrayList<Student>studentList);
	
//Reports
	public void viewCourse(ArrayList<Course>courseList);
	public void viewAllFull(ArrayList<Course>list);
	public void writeToFile(ArrayList<Course>list);
	public void viewStudentName(ArrayList<Course>list);
	public void aStudentRegistered(ArrayList<Student>list);
	public void sortCourse(ArrayList<Course>courseList);
}