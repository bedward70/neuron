package ru.bedward70.neuron.layer;

import ru.bedward70.neuron.Neuron;
import ru.bedward70.neuron.function.ResultFunctionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NeronLayer {

	private static final double BIAS_DEFAULT = 1.;
	private static final Random RANDOM = new Random();

	public static NeronLayer create(final ResultFunctionType functionType, final int count, final Collection<Number> inputs, final boolean bias) {
		final List<Neuron> neurons = new ArrayList<Neuron>();

		for (int i = 0; i < count; i++) {
			final Neuron neuron = Neuron.create(functionType);
			final Collection<Number> neuronInputs = new ArrayList<Number>(inputs);
			if (bias) {
				neuronInputs.add(BIAS_DEFAULT);
			}
			final Collection<Number> neuronWeighs = getWeighs(neuronInputs.size());
			neuron.setValues(neuronInputs);
			neuron.setWeighs(neuronWeighs);

			neurons.add(neuron);
		}

		return new NeronLayer(neurons);
	}

	private static Collection<Number> getWeighs(final int size) {
		final Collection<Number> result = new ArrayList<Number>();
		for (int i = 0; i < size; i++) {
			result.add(0.5 + RANDOM.nextDouble() * 0.5);
		}
		return result;
	}

	private final List<Neuron> neurons;

	private NeronLayer(final List<Neuron> neurons) {
		this.neurons = neurons;
	}

	public List<Neuron> getNeurons() {
		return this.neurons;
	}

	public List<Number> getOutValus() {
		return new ArrayList<Number>(this.neurons);
	}

	@Override
	public String toString() {

		final StringBuffer sb = new StringBuffer();
		sb.append("NeronLayer [neurons=\n");
		for (final Neuron neuron : this.neurons) {
			sb.append("    " + neuron + "\n");
		}
		sb.append("]");
		return sb.toString();
	}

	public void clearGrades() {

		for (final Neuron neuron : this.neurons) {
			neuron.clearGrades();
		}
	}

	public void learn(final double rate) {

		for (final Neuron neuron : this.neurons) {
			neuron.learn(rate);
		}
	}

	public double getMaxGrade() {

		final Collection<Double> result = new ArrayList<Double>();
		for (final Neuron neuron : this.neurons) {
			result.add(neuron.getMaxGrade());
		}
		return result.isEmpty() ? 0 : Collections.max(result);
	}
}
