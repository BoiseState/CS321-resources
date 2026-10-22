
public class TestRandom
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		UniversalHashFamily f1 = new UniversalHashFamily();
		CryptographicallySecureHash f2 = new CryptographicallySecureHash();

		int tableSize = 997; // prime closest to 1000

		System.out.println("UniversalHashFamily output:");
		for (int i = 0; i < 10; i++) {
			System.out.println(f1.hash(new String("Object" + i), tableSize));
		}

		System.out.println("CryptographicallySecureHash output:");
		for (int i = 0; i < 10; i++) {
			System.out.println(f2.hash(new String("Object" + i), tableSize));
		}

	}

}
