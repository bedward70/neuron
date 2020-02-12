package ru.bedward70.neuron;

import junit.framework.TestCase;
import ru.bedward70.neuron.function.ResultFunctionType;
import ru.bedward70.neuron.layer.NeronLayerDescription;
import ru.bedward70.neuron.net.NeuronNet;
import ru.bedward70.neuron.probation.NeuronNetLearnFunctionStatic;
import ru.bedward70.neuron.probation.NeuronNetProbation;

import java.util.Arrays;

public class NeuronNetProbationTest extends TestCase {

	public void test() throws Exception {

		final double[][] trainSet = new double[][] {
			{0.98, 0.94, 0.95},
			{0.60, 0.60, 0.85},
			{0.35, 0.15, 0.15},
			{0.25, 0.30, 0.98},
			{0.75, 0.85, 0.91},
			{0.43, 0.57, 0.87},
			{0.05, 0.06, 0.01}
		};
		final double[][] realOutputSet = new double[][] {
			{0.80},
			{0.59},
			{0.23},
			{0.45},
			{0.74},
			{0.63},
			{0.10}
		};

		final NeuronNet net = NeuronNet.create(trainSet[0].length, new NeronLayerDescription(ResultFunctionType.LINEAR, 1, true));

		final NeuronNetProbation probation = new NeuronNetProbation();
		probation.setSets(trainSet, realOutputSet);
		probation.setMaxEpochs(20);
		probation.setTargetError(0.00001);
		probation.setLearningRate(new NeuronNetLearnFunctionStatic(0.4), true);

		probation.train(net);

		//System.out.println(probation.getOutErrors().get(probation.getOutErrors().size() - 1));
		System.out.println(Arrays.deepToString(probation.getOutErrors().toArray()).replace(" ", "\n") );
//		for (final double d : probation.getOutErrors()) {
//			System.out.println(d);
//		}

//		final Chart c1 = new Chart();
//		c1.plotXYData(probation.getOutErrors().toArray(), "MSE Error", "Epochs", "MSE Value");

		System.out.println(net);

//		assertEquals(22., neuron.doubleValue());
	}
}
