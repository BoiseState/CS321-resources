import java.io.Serializable;


public class StudentRecord implements Serializable
{
	//auto generated using the Eclipse IDE
	private static final long serialVersionUID = -1676113151880982053L;
	
	private String name;
	private int id;
	
	public StudentRecord(String name, int id)
	{
		this.name = name;
		this.id = id;
	}
	
	public String toString()
	{
		return id + ":" + name;
	}

	public int getId() {
		return id;
	}

}
