

public class User implements java.io.Serializable{
	/*
	 * username, password, first name, and last name
	 */
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public User(){
		}
	public User(String uname,String pass,String first,String last){
		this.username=uname;
		this.password=pass;
		this.firstname=first;
		this.lastname=last;
	}
	
	public void setUsername(String uname){
		this.username=uname;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String pass){
		this.password=pass;
	}
	public String getPassword(){
		return password;
	}
	public void setFirstname(String first){
		this.firstname=first;
	}
	public String getFirstname(){
		return firstname;
	}
	public void setLastname(String last){
		this.lastname=last;
	}
	public String getLastname(){
		return lastname;
	}
	
}
