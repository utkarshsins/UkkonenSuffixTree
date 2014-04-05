public class GlobalUpdate {

	private int value;

	public int getValue() {
		return value;
	}
	public GlobalUpdate()
	{
		value=-1;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void increment() {
		this.value++;
	}
}
