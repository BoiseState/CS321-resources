

public class Record implements Comparable, DiskSize
{
    private int key;
    private double value1;
    private double value2;
    private double value3;

	public Record() 
	{
		this.key = 0;
		this.value1 =  0;
		this.value2 = 0;
		this.value3 = 0;
	}

	public Record (int key, double value1, double value2, double value3)
	{
		this.key = key;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

	public int getKey() { return key;}

	public int getDiskSize() { return 28; }

	public String toString() {
		return "key="+key+" value1="+value1+" value2="+value2+" value3="+value3;
	}

	public int compareTo(Object obj)
	{
		if (obj instanceof Record) {
			Record other = (Record) obj;
			return other.getKey() - this.getKey();
		} else {
			throw new IllegalArgumentException();
		}
	}


}
