
public class AnotherClass
{
    private InnerClassExample.Node node;

    public AnotherClass(InnerClassExample.Node node) {
	this.node = node;
    }
    
    public String toString() {
	return "AnotherClass[" + node.toString() + "]";
    }
}
