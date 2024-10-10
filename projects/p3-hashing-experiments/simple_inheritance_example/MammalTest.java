package simple_inheritance_example;

public class MammalTest {

	public static void main(String[] args) {

			Dog dog1 = new Dog("Leo", 1);
			Cat cat1 = new Cat("Leela", 3);
			
			System.out.println("MammalTest: " + dog1.speak());
			System.out.println("MammamlTest: " + cat1.speak());
			
			Mammal m = dog1;
			System.out.println("MammalTest: " + m.speak());
			
			m = cat1;
			System.out.println("MammalTest: " + m.speak());
	}

}
