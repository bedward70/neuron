package ru.bedward70.neuron.probation;

public class NeuronNetLearnFunctionLine implements NeuronNetLearnFunction {

	private final double startLearningRate;
	private final double stopLearningRate;

	public NeuronNetLearnFunctionLine(final double startLearningRate, final double stopLearningRate) {
		super();
		this.startLearningRate = startLearningRate;
		this.stopLearningRate = stopLearningRate;
	}

	@Override
	public double getLearningRate(final int epoch, final int maxEpochs) {

		return this.startLearningRate * (maxEpochs - epoch) / maxEpochs + this.stopLearningRate * (epoch) / maxEpochs;
	}
}