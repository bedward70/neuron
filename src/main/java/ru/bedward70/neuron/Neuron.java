package ru.bedward70.neuron;

import ru.bedward70.neuron.function.ResultFunctionType;

import java.util.Collection;

public abstract class Neuron extends Number {

	/** serialVersionUID */
	private static final long serialVersionUID = -6566573782245363715L;

	public static Neuron create(final ResultFunctionType functionType) {
		return new NeuronImpl(functionType);
	}

	public abstract void setWeighs(final Collection<Number> inputWeighs);

	public abstract void setValues(final Collection<Number> inputItem);

	public abstract void clearGrades();
	public abstract double getMaxGrade();
	public abstract void backpropagation(double error);
	public abstract void learn(double rate);

}