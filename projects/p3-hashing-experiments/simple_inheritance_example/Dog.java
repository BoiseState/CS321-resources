package simple_inheritance_example;

public class Dog extends Mammal {

	public Dog(String name, int age) {
		super(name, age);
	}

	@Override
	public String speak() {
		return "woof woof! I am " + name;
	}
}
