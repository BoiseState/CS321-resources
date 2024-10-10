package simple_inheritance_example;

public class Cat extends Mammal {

	public Cat(String name, int age) {
		super(name, age);
	}

	@Override
	public String speak() {
		return "meow meow! I am " + name;
	}
	
}
