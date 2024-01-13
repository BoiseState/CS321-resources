/**
 * A dummy object that assists with unit tests.
 *
 *  @author CS321 Instructors
 */
public class TestObject implements KeyInterface<Integer>{

    private int key;

    public TestObject(int key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TestObject) {
            TestObject t = (TestObject) o;
            return t.getKey() == key;
        } else {
            return false;
        }
    }
}
