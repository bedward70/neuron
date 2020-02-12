package ru.bedward70.neuron.layer;

import ru.bedward70.neuron.function.ResultFunctionType;

public class NeronLayerDescription {

	private final ResultFunctionType functionType;
	private final int count;
	private final boolean bias;

	public NeronLayerDescription(final ResultFunctionType functionType, final int count, final boolean bias) {
		super();
		this.functionType = functionType;
		this.count = count;
		this.bias = bias;
	}

	public ResultFunctionType getFunctionType() {
		return this.functionType;
	}

	public int getCount() {
		return this.count;
	}

	public boolean isBias() {
		return this.bias;
	}
}
