package cn.lxd;

public class Student {
	private int id;
	private String name;
	private String call;
	private int sex;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String call, int sex) {
		super();
		this.id = id;
		this.name = name;
		this.call = call;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", call=" + call + ", sex=" + (sex==0?"ÄÐ":"Å®")+"]";
	}
}
