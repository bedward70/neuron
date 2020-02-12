package ru.bedward70.neuron.net;

import ru.bedward70.neuron.Neuron;
import ru.bedward70.neuron.layer.NeronLayer;
import ru.bedward70.neuron.layer.NeronLayerDescription;
import ru.bedward70.neuron.value.InputValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NeuronNet {


	public static NeuronNet create(final int inputCount, final NeronLayerDescription... descriptions) {

		final List<InputValue> inputValues = createImputValues(inputCount);
		final List<NeronLayer> layers = new ArrayList<NeronLayer>();
		Collection<Number> inputs = new ArrayList<Number>(inputValues);
		for (final NeronLayerDescription description : descriptions) {
			final NeronLayer layer = NeronLayer.create(description.getFunctionType(), description.getCount(), inputs, description.isBias());
			layers.add(layer);
			//
			inputs = layer.getOutValus();
		}
		return new NeuronNet(inputValues, layers);
	}

	private static List<InputValue> createImputValues(final int inputCount) {
		final List<InputValue> result = new ArrayList<InputValue>();
		for (int i = 0; i < inputCount; i++) {
			result.add(new InputValue());
		}
		return result;
	}

	private final List<InputValue> inputValues;
	private final List<NeronLayer> layers;

	public NeuronNet(final List<InputValue> inputValues, final List<NeronLayer> layers) {
		super();
		this.inputValues = inputValues;
		this.layers = layers;
	}


	public List<InputValue> getInputValues() {
		return this.inputValues;
	}

	public List<NeronLayer> getLayers() {
		return this.layers;
	}

	public List<Neuron> getNeurons() {
		return this.layers.get(this.layers.size() - 1).getNeurons();
	}

	public List<Number> getOutValus() {
		return this.layers.get(this.layers.size() - 1).getOutValus();
	}

	public void clearGrades() {
		for (final NeronLayer layer : this.layers) {
			layer.clearGrades();
		}
	}

	public void learn(final double rate) {
		for (final NeronLayer layer : this.layers) {
			layer.learn(rate);
		}
	}

	public double getMaxGrade() {

		final Collection<Double> result = new ArrayList<Double>();
		for (final NeronLayer layer : this.layers) {
			result.add(layer.getMaxGrade());
		}
		return result.isEmpty() ? 0 : Collections.max(result);
	}

	@Override
	public String toString() {

		final StringBuffer sb = new StringBuffer();
		sb.append("NeuronNet [\n");
		sb.append(" inputValues=" + this.inputValues + "\n");
		sb.append(" layers=[\n");
		for (final NeronLayer layer : this.layers) {
			sb.append("  " + layer + "\n");
		}
		sb.append("]]");
		return sb.toString();
	}
}
