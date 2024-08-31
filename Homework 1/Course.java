import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Course implements Cloneable,java.io.Serializable{
	
	private String courseName;
	private String courseID;
	private int maxStu;
	private int currentStu;
	
	//List of Name is understood as username in this case; 
	private String listOfName;

	private String instructor;
	private int sectionNum;
	private String location;
	private static final long serialVersionUID = 1L;
	
	public Course(){
		
	}
	
	//Admin course constructor: No list of names, no names in course,current student is 0
	public Course(String courseName, String courseID, String maxStu,String instructor, 
			String sectionNum, String location){
		this.courseName=courseName;
		this.courseID=courseID;
		this.maxStu=Integer.parseInt(maxStu);
		this.currentStu=0;
		this.listOfName="null";
		this.instructor=instructor;
		this.sectionNum=Integer.parseInt(sectionNum);
		this.location=location;	
			
	}
	
	//Load CSV file: one time; list of names is null,no names in course
	public Course(String courseName, String courseID, int maxStu, int currentStu, String listOfNames,
			String instructor, int sectionNum, String location) {
		this.courseName=courseName;
		this.courseID=courseID;
		this.maxStu=maxStu;
		this.currentStu=currentStu;
		this.listOfName="null";
		this.instructor=instructor;
		this.sectionNum=sectionNum;
		this.location=location;	
	}
	
	
	public void print(){
		System.out.println("Course Name: " + this.getCourseName());
		System.out.println("Course ID: " + this.getCourseID());
		System.out.println("Maximum Student: " + this.getMaxStu());
		System.out.println("currentStu: " + this.getCurrentStuNum());
		System.out.println("List of Names: " + this.getListOfNames());
		System.out.println("Instructor: " + this.getInstructor());
		System.out.println("Section Number: " + this.getSectionNum());
		System.out.println("Location: " + this.getLocation() + "\n");
	}
	
	public void printViewCourse(){
		System.out.println("Course Name: " + this.getCourseName());
		System.out.println("Course ID: " + this.getCourseID());
		System.out.println("Maximum Student: " + this.getMaxStu());
		System.out.println("currentStu: " + this.getCurrentStuNum() + "\n");
	}
	
	public void registerStu(Student registerS, String firstname,String lastname,ArrayList<Student>studentList){

		this.currentStu++;
		
		while(!((this.listOfName).equals("null"))){
			System.out.println("list of names is not null");
			StringBuilder sb=new StringBuilder(this.listOfName);		
				
				//change list of names
				sb.append(registerS.getUsername());
				sb.append(",");
				this.listOfName=sb.toString();
				
				break;
			}
			
		if((this.listOfName).equals("null")){
			this.listOfName=registerS.getUsername();
		}
	}
	
	public void withdrawStu(Student withdrawS){
		
		this.currentStu--;
		Scanner withdrawStu = new Scanner(this.listOfName).useDelimiter("[,\n]");
		StringBuilder sb=new StringBuilder();
		String sName=withdrawS.getUsername();
		
		while(withdrawStu.hasNext()){
			if(!((withdrawStu.next()).equals(sName))){
				sb.append(withdrawStu.next());
				sb.append(",");
			}
		}
		this.listOfName=sb.toString();
	}
	
	public void setCourseName(String courseName){
		this.courseName=courseName;
	}
	public String getCourseName(){
		return this.courseName;
	}
	public void setCourseID(String ID){
		this.courseID=ID;
	}
	public String getCourseID(){
		return this.courseID;
	}
	public void setMaxStu(int max){
		this.maxStu=max;
	}
	public int getMaxStu(){
		return this.maxStu;
	}
	
	public void setCurrentStuNum(int currentNum){
		this.currentStu=currentNum;
	}
	public int getCurrentStuNum(){
		return this.currentStu;
	}
	
	public String getListOfNames(){
		return this.listOfName;
	}
	
	public void setInstructor(String instructor){
		this.instructor=instructor;
	}
	public String getInstructor(){
		return this.instructor;
	}
	public void setSectionNum(int sectionNum){
		this.sectionNum=sectionNum;
	}
	public int getSectionNum(){
		return this.sectionNum;
	}
	public void setLocation(String location){
		this.location=location;
	}
	public String getLocation(){
		return this.location;
	}
	
	public Course clone(){
		Course duplicate=new Course(this.getCourseName(), this.getCourseID(), this.getMaxStu(), 
				this.getCurrentStuNum(), this.getListOfNames(), this.getInstructor(),
				this.getSectionNum(),this.getLocation());
		return duplicate;
	}
	
}