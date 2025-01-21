//Model class for User
public class User {
	private int id;
	private String username;
	private String password;
	private boolean admin;
	private boolean active;

	public User(int id, String username, String password, boolean admin, boolean active){
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.active = active;
	}
	
	public User(){
		
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}