import java.io.*;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationManager {
    private static final String COURSE_LIST_FILE = "courseList.ser";
    private static final String STUDENT_LIST_FILE = "studentList.ser";

    public static void serializeCourseList(ArrayList<Course> courseList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSE_LIST_FILE))) {
            oos.writeObject(courseList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static ArrayList<Course> deserializeCourseList() {
        ArrayList<Course> courseList = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSE_LIST_FILE))) {
            courseList = (ArrayList<Course>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return courseList;
    }
    
    public static void serializeStudentList(ArrayList<Student> studentList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENT_LIST_FILE))) {
            oos.writeObject(studentList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static ArrayList<Student> deserializeStudentList() {
        ArrayList<Student> studentList = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENT_LIST_FILE))) {
            studentList = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}