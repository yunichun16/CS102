import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Token {
    
    public ArrayList<Course> loadCoursesFromCSV() throws IOException {
        ArrayList<Course> courseList = new ArrayList<>();
        String csvFile = "MyUniversityCourses.csv";    
        
        try (Scanner sc = new Scanner(new File(csvFile))) {
            sc.nextLine(); // Skip header line
            while(sc.hasNextLine()){    
                String input = sc.nextLine(); // Use nextLine() directly
                StringTokenizer strTokens = new StringTokenizer(input,",");
                        
                while (strTokens.hasMoreTokens()){
                    try {
                        String courseName = strTokens.nextToken();
                        String courseID = strTokens.nextToken();
                        int maxStu = Integer.parseInt(strTokens.nextToken());
                        int currentStu = Integer.parseInt(strTokens.nextToken());
                        String listOfNames = strTokens.nextToken();
                        String instructor = strTokens.nextToken();
                        int sectionNum = Integer.parseInt(strTokens.nextToken());
                        String location = strTokens.nextToken();
                        
                        Course c = new Course(courseName, courseID, maxStu, currentStu, listOfNames,
                                              instructor, sectionNum, location);
                        courseList.add(c);
                    } catch (NumberFormatException e) {
                        e.printStackTrace(); // Handle parsing errors
                    }
                }
            }
        }
        
        return courseList; // Return the populated list
    }
}
