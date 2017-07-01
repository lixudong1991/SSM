package cn.lxd;

public class Vide
{
	private int id;
	private String name;
	public Vide(int id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}
	public Vide()
	{
		super();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
}
