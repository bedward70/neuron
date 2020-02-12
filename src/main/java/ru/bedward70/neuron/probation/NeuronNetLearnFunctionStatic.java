package ru.bedward70.neuron.probation;

public class NeuronNetLearnFunctionStatic implements NeuronNetLearnFunction {

	private final double learningRate;

	public NeuronNetLearnFunctionStatic(final double learningRate) {
		super();
		this.learningRate = learningRate;
	}


	@Override
	public double getLearningRate(final int epoch, final int maxEpochs) {

		return this.learningRate;
	}
}