package ru.bedward70.neuron.probation;

import ru.bedward70.neuron.net.NeuronNet;
import ru.bedward70.neuron.value.InputValue;

import java.util.ArrayList;
import java.util.List;

public class NeuronNetProbation {

	private double[][] trainSet;
	private double[][] realOutputSet;

	private double targetError;
	private int maxEpochs;
	private NeuronNetLearnFunction learningRateFunction;

	private final List<Double> outErrors = new ArrayList<Double>();

	private boolean normalizeByError;

	public void setSets(final double[][] trainSet, final double[][] realOutputSet) {
		this.trainSet = trainSet;
		this.realOutputSet = realOutputSet;
	}

	public void setTargetError(final double targetError) {
		this.targetError = targetError;

	}

	public void setMaxEpochs(final int maxEpochs) {
		this.maxEpochs = maxEpochs;
	}

	public void setLearningRate(final NeuronNetLearnFunction learningRateFunction, final boolean normalizeByError) {
		this.learningRateFunction = learningRateFunction;
		this.normalizeByError = normalizeByError;
	}

	public List<Double> getOutErrors() {
		return this.outErrors;
	}

	public void train(final NeuronNet net) {

		this.outErrors.clear();
		for (int epoch = 0 ; epoch < this.maxEpochs; epoch++) {

			final double[][] probationResult = calculate(net);

			final double mainError = calculateError(probationResult);
			this.outErrors.add(mainError);
			if (mainError < this.targetError) {
				break;
			}

			learn(epoch, net, mainError);
		}
	}

	private void learn(final int epoch, final NeuronNet net, final double mainError) {

		net.clearGrades();
		for (int probation = 0 ; probation < this.trainSet.length; probation++) {
			setData(net.getInputValues(), probation);
			for (int i = 0 ; i < this.realOutputSet[probation].length; i++) {
				final double error = this.realOutputSet[probation][i] - net.getOutValus().get(i).doubleValue();
				net.getNeurons().get(i).backpropagation(error);
			}
		}
		final double maxGrade = this.normalizeByError ? (net.getMaxGrade() / mainError) : 1.;
		if (maxGrade != 0.) {
			net.learn(this.learningRateFunction.getLearningRate(epoch, this.maxEpochs) / maxGrade);
		}
	}

	private void setData(final List<InputValue> inputValues, final int probation) {
		final double[] data = this.trainSet[probation];
		for (int i = 0 ; i < data.length; i++) {
			inputValues.get(i).setValue(data[i]);
		}
	}

	private double calculateError(final double[][] probationResult) {
		double error = 0;
		for (int probation = 0 ; probation < this.realOutputSet.length; probation++) {
			double errorProbation = 0;
			for (int i = 0 ; i < this.realOutputSet[probation].length; i++) {
				final double diff = this.realOutputSet[probation][i] - probationResult[probation][i];
				errorProbation += Math.pow(diff, 2);
			}
			errorProbation = Math.sqrt(errorProbation);
			error += errorProbation;
		}
		return error /  this.realOutputSet.length;
	}

	private double[][] calculate(final NeuronNet net) {
		final double[][] result = new double[this.realOutputSet.length][this.realOutputSet[0].length];
		for (int probation = 0 ; probation < this.trainSet.length; probation++) {
			setData(net.getInputValues(), probation);

			final List<Number> outValuse = net.getOutValus();
			for (int i = 0 ; i < result[probation].length; i++) {
				result[probation][i] = outValuse.get(i).doubleValue();
			}
		}
		return result;
	}
}
