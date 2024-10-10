package simple_inheritance_example;

public abstract class Mammal {
	protected String name;
	protected int age;
	
	public Mammal (String name, int age)
	{
		this.name = name;
		this.age = age;
	}
	
	public abstract String speak();
	
	public String toString() {
		return "name = " + name + " age = " + age;
	}
}
