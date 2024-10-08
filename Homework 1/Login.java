import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.Serializable;

public class Login implements java.io.Serializable{
	static ArrayList<Course> courseList=new ArrayList<Course>();
	static ArrayList<Student> studentList=new ArrayList<Student>();
	static Scanner scanner=new Scanner(System.in);
	static Scanner sc=new Scanner(System.in).useDelimiter("[,\n]");
	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args) throws IOException {
		// Check the path and existence of the CSV file
		String csvFile = "MyUniversityCourses.csv";
		File csvF = new File(csvFile);
		System.out.println("Absolute path to the CSV file: " + csvF.getAbsolutePath());
		if (csvF.exists()) {
			System.out.println("CSV file found. Attempting to read...");
			// Read the first line for debugging
			try (Scanner sc = new Scanner(csvF)) {
				if (sc.hasNextLine()) {
					System.out.println("First line of the CSV file: " + sc.nextLine());
				} else {
					System.out.println("CSV file is empty or cannot be read.");
				}
			} catch (FileNotFoundException e) {
				System.out.println("Error: The CSV file was not found.");
				e.printStackTrace();
			}
		} else {
			System.out.println("CSV file not found at specified path.");
		}
	
		// Populate the courseList from the CSV file
		fillCourseList();
	
		File serF = new File("courseList.ser");
		System.out.println("Absolute path to the serialized file: " + serF.getAbsolutePath());
		if (!serF.exists()) {
			System.out.println("Serialized file not found. Will attempt to serialize courseList.");
			SerializationManager.serializeCourseList(courseList);
			System.out.println("Course list serialized to courseList.ser");
		} else {
			System.out.println("Serialized file found. Attempting to deserialize...");
			courseList = SerializationManager.deserializeCourseList();
			System.out.println("Course list size after deserialization: " + (courseList != null ? courseList.size() : "null"));
		}
		
		Scanner scanner = new Scanner(System.in);
		String identity, studentAns, studentUsername = "", studentPassword = "";
		boolean loginSuccess = false;
		boolean validOption = false;
		
		System.out.println("Please enter your identity as Admin or Student");
		identity = scanner.nextLine();
		
		if ("Student".equals(identity)) {
			System.out.println("Are you already registered in the system?");
			studentAns = scanner.nextLine();
		
			if ("No".equals(studentAns)) {
				// Prompt for registration details
				System.out.println("Please enter your first name:");
				String firstName = scanner.nextLine();
			
				System.out.println("Please enter your last name:");
				String lastName = scanner.nextLine();
			
				System.out.println("Please choose a username:");
				String newUsername = scanner.nextLine();
			
				// Check if the username is already taken
				for (Student existingStudent : studentList) {
					if (existingStudent.getUsername().equals(newUsername)) {
						System.out.println("Username is already taken. Please choose a different one.");
						return; // Exit the current flow and potentially prompt them again
					}
				}
			
				System.out.println("Please choose a password:");
				String newPassword = scanner.nextLine();
			
				// Create the new Student object
				Student newStudent = new Student(firstName, lastName, newUsername, newPassword);
			
				// Add the new Student to the list
				studentList.add(newStudent);
			
				// Optionally save the new list to file
				SerializationManager.serializeStudentList(studentList);
			
				System.out.println("Registration successful!");
			
				// Log the new student in by getting their index in the studentList
				int sIndex = studentList.indexOf(newStudent);
				StudentMenu(sIndex); // Call the student menu with the index
			} else if ("Yes".equals(studentAns)) {
				do {
					System.out.println("Please enter your username");
					studentUsername = scanner.nextLine();
		
					System.out.println("Please enter your password");
					studentPassword = scanner.nextLine();
		
					int sIndex = -1; // Initialize to an invalid index
					for (Student loginS : studentList) {
						if (loginS.getUsername().equals(studentUsername)
								&& loginS.getPassword().equals(studentPassword)) {
							sIndex = studentList.indexOf(loginS);
							loginSuccess = true;
							break; // Found the student, break the loop
						}
					}
		
					if (loginSuccess) {
						StudentMenu(sIndex); // Call the student menu with the index
					} else {
						System.out.println("Incorrect username or password. Please try again.");
					}
				} while (!loginSuccess);
			} else {
				System.out.println("Invalid response. Please answer 'Yes' or 'No'.");
			}
		} else if ("Admin".equals(identity)) {
			String username = "", password = "", option;
			// Loop until the username and password are correct
			do {
				System.out.println("Please enter your username");
				username = scanner.nextLine();
		
				if (!"Admin".equals(username)) {
					System.out.println("Incorrect username. Please try again.");
				} else {
					System.out.println("Please enter your password");
					password = scanner.nextLine();
		
					if ("Admin001".equals(password)) {
						loginSuccess = true; // Correct username and password
					} else {
						System.out.println("Incorrect password. Please try again.");
					}
				}
			} while (!loginSuccess);
		
			// Post-login logic
			Admin a = new Admin(username, password, null, null);
			
			// Loop until the option is valid
			do {
				System.out.println("Course Management or Report");
				option = scanner.nextLine();
		
				if ("Course Management".equals(option)) {
					AdminCourseManagement(a);
					validOption = true;
				} else if ("Report".equals(option)) {
					AdminReport(a);
					validOption = true;
				} else {
					System.out.println("No such option. Please re-enter");
				}
			} while (!validOption);
		
		} else {
			// Handle case for incorrect identity
			System.out.println("Invalid identity entered. Only Admin or Student is allowed.");
		}
	}
		
			
	public static void StudentMenu(int sIndex) {
		System.out.println("View all Courses, View all Courses not full, Register Course, "
				+ "Withdraw Course, View all Registered, Exit");
		System.out.println("Please enter your option from the menu");
		String menu=scanner.nextLine();
		Student s=studentList.get(sIndex);
		while(!(menu).equals("Exit")){
			switch(menu){
			case "View all Courses":s.viewAllCourse(courseList);
									break;
			case "View all Courses not full":s.viewNotFull(courseList);
									break;
			case "Register Course":s.register(courseList,studentList);
									break;
			case "Withdraw Course":s.withdraw(courseList,studentList);
									break;
			case "View all Registered":s.viewAllRegistered();
									break;
			}
			System.out.println("Please enter another option from the menu");
			menu=scanner.nextLine();
		}
		if(menu.equals("Exit")){
			SerializationManager.serializeStudentList(studentList);
			SerializationManager.serializeCourseList(courseList);
		}
		System.out.println("You have logged out");
		
	}

	public static void AdminCourseManagement(Admin a) {
		System.out.println("Menu:Create a new Course, Delet a Course, Edit a Course,"
				+ "Display, Register a Student, Exit");
		System.out.println("Please enter your option from the menu");
		int editIndex=0;
		String menu=scanner.nextLine();
		while(!(menu).equals("Exit")){
		switch(menu){
		case "Create a new Course":courseList.add(a.createCourse());
									break;
		case "Delet a Course":a.deleteCourse(courseList);
									break;
		case "Edit a Course":a.editCourse(courseList);
									break;
		case "Display": a.display(courseList);
									break;
		case "Register a Student":a.registerStudent(studentList);
									break;						
		}
		System.out.println("Please enter another option from the menu");
		menu=scanner.nextLine();
		}
		if(menu.equals("Exit")){
			SerializationManager.serializeStudentList(studentList);
			SerializationManager.serializeCourseList(courseList);
		}
		System.out.println("You have logged out");
	}
	
	public static void AdminReport(Admin a){
		System.out.println("Menu: View all Course,View all Full,Write to File,View names of "
				+ "Students for a course, View registered courses of a student, Sort Courses, Exit");
		System.out.println("Please enter your option from the menu");
		String menu=scanner.nextLine();
		while(!(menu).equals("Exit")){
		switch(menu){
		case "View all Course":a.viewCourse(courseList);;
								break;
		case "View all Full":a.viewAllFull(courseList);
								break;
		case "Write to File":a.writeToFile(courseList);
								break;
		case "View names of Students for a course":a.viewStudentName(courseList);
								break;
		case "View registered courses of a student":a.aStudentRegistered(studentList);
								break;
		case "Sort Courses":a.sortCourse(courseList);
								break;
		}
		System.out.println("Please enter another option from the menu");
		menu=scanner.nextLine();
		}
		if(menu.equals("Exit")){
			SerializationManager.serializeStudentList(studentList);
			SerializationManager.serializeCourseList(courseList);
				System.out.println("You have logged out");
		}
	}
	
	public static void fillCourseList() throws IOException {
		String csvFile = "MyUniversityCourses.csv";
		Scanner sc = new Scanner(new File(csvFile));
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			StringTokenizer strTokens = new StringTokenizer(input, ",");
		
			if (strTokens.countTokens() >= 8) {
				String courseName = strTokens.nextToken();
				String courseID = strTokens.nextToken();
				int maxStu = Integer.parseInt(strTokens.nextToken());
				
				// Check if the current number of students token is empty
				String currentStuToken = strTokens.nextToken().trim(); // Trim to handle leading/trailing spaces
				int currentStu = currentStuToken.isEmpty() ? 0 : Integer.parseInt(currentStuToken);
				
				String listOfNames = strTokens.nextToken();
				String instructor = strTokens.nextToken();
				int sectionNum = Integer.parseInt(strTokens.nextToken());
				String location = strTokens.nextToken();
		
				Course c = new Course(courseName, courseID, maxStu, currentStu, listOfNames, instructor, sectionNum, location);
				courseList.add(c);
			} else {
				System.out.println("Incomplete entry in CSV file: " + input);
			}
		}
	}
}