package ru.bedward70.neuron;

import ru.bedward70.neuron.function.ResultFunctionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NeuronImpl extends Neuron {

	/** */
	private static final long serialVersionUID = -2365618361023494227L;

	private final List<Number> weights = new ArrayList<Number>();
	private final List<Double> weightGrades = new ArrayList<Double>();
	private final List<Number> values = new ArrayList<Number>();

	private final ResultFunctionType functionType;

	NeuronImpl(final ResultFunctionType functionType) {
		super();
		this.functionType = functionType;
	}

	@Override
	public int intValue() {
		return (int) doubleValue();
	}

	@Override
	public long longValue() {
		return (long) doubleValue();
	}

	@Override
	public float floatValue() {
		return (float) doubleValue();
	}

	@Override
	public synchronized double doubleValue() {

		double result = 0.;
		for (int i = 0; i < this.weights.size(); i++) {
			result += this.weights.get(i).doubleValue() * this.values.get(i).doubleValue();
		}
		return this.functionType.getActivation().apply(result);
	}

	@Override
	public synchronized void setWeighs(final Collection<Number> inputWeighs) {

		this.weights.clear();
		this.weights.addAll(inputWeighs);
	}

	@Override
	public synchronized void setValues(final Collection<Number> inputItem) {

		this.values.clear();
		this.values.addAll(inputItem);
	}

	@Override
	public synchronized void backpropagation(final double error) {

		//https://habrahabr.ru/company/paysto/blog/246093/
		final double outValue = doubleValue();
		final double internalOutValue = this.functionType.getDeactivative().apply(outValue) * error;
		for (int i = 0; i < this.weights.size(); i++) {

			// Weight
			backpropagationWeight(internalOutValue, i);

			// Value
			if (this.values.get(i) instanceof Neuron) {
				backpropagationNeuron(internalOutValue, i);
			}
		}
	}

	private void backpropagationNeuron(final double internalOutValue, final int i) {

		final double valueGrad = internalOutValue * this.weights.get(i).doubleValue();
		((Neuron) this.values.get(i)).backpropagation(valueGrad);
	}

	private void backpropagationWeight(final double internalOutValue, final int i) {

		final double weightGrad = internalOutValue * this.values.get(i).doubleValue();
		while (i >= this.weightGrades.size()) {
			this.weightGrades.add(0.);
		}
		this.weightGrades.set(i,  this.weightGrades.get(i) + weightGrad);
	}

	@Override
	public synchronized void learn(final double rate) {

		//https://habrahabr.ru/company/paysto/blog/246093/
		final List<Number> newWeights = new ArrayList<Number>();
		for (int i = 0; i < this.weights.size(); i++) {
			if (i < this.weightGrades.size()) {
				newWeights.add(
					this.weights.get(i).doubleValue()
					+ this.weightGrades.get(i).doubleValue() * rate
				);
				this.weightGrades.set(i, 0.);
			} else {
				newWeights.add(this.weights.get(i));
			}
		}

		// As event
		setWeighs(newWeights);
	}

	@Override
	public synchronized void clearGrades() {
		this.weightGrades.clear();
	}

	@Override
	public synchronized double getMaxGrade() {
		final Collection<Double> result = new ArrayList<Double>();
		for (final Double value : this.weightGrades) {
			result.add(Math.abs(value));
		}
		return result.isEmpty() ? 0 : Collections.max(result);
	}


	@Override
	public String toString() {
		return "NeuronImpl [weights=" + this.weights + ", grades=" + this.weightGrades + ", values=" + this.values
				+ ", type=" + this.functionType + "]";
	}


}