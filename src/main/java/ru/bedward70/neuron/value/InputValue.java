package ru.bedward70.neuron.value;

public class InputValue extends Number {

	private double value;

	public void setValue(final double value) {
		this.value = value;
	}

	@Override
	public int intValue() {
		return (int) this.value;
	}

	@Override
	public long longValue() {
		return (long) this.value;
	}

	@Override
	public float floatValue() {
		return (float) this.value;
	}

	@Override
	public double doubleValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "InputValue [value=" + this.value + "]";
	}
}
