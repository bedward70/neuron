package ru.bedward70.neuron;

import junit.framework.TestCase;
import ru.bedward70.neuron.function.ResultFunctionType;
import ru.bedward70.neuron.value.InputValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbcLearning2Test extends TestCase {

	private static final ResultFunctionType TYPE = ResultFunctionType.SIGLOG;
	private static final double BIAS_DEFAULT = 1.;

	private final double[][] TRAIN_SET = new double[][] {
		{0.00, 0.00},
		{0.00, 1.00},
		{1.00, 0.00},
		{1.00, 1.00}
	};
	private final double[][] REAL_OUTPUT_SET = new double[][] {
		{0.00},
		{1.00},
		{1.00},
		{0.00},
	};


	public void test() throws Exception {


		// Inputs
		final InputValue x1 = new InputValue();
		final InputValue x2 = new InputValue();

		// External level
		final Neuron n3 = Neuron.create(TYPE);
		final Collection<Number> n3Inputs = new ArrayList<Number>();
		final Collection<Number> n3Weighs = new ArrayList<Number>();

		// W13
		n3Inputs.add(x1);
		n3Weighs.add(0.50);

		// W23
		n3Inputs.add(x2);
		n3Weighs.add(-0.50);

		// W3
		n3Inputs.add(BIAS_DEFAULT);
		n3Weighs.add(-0.30);
		n3.setValues(n3Inputs);
		n3.setWeighs(n3Weighs);

		// External level
		final Neuron n4 = Neuron.create(TYPE);
		final Collection<Number> n4Inputs = new ArrayList<Number>();
		final Collection<Number> n4Weighs = new ArrayList<Number>();

		// W23
		n4Inputs.add(x1);
		n4Weighs.add(0.30);

		// W24
		n4Inputs.add(x2);
		n4Weighs.add(0.50);

		// W4
		n4Inputs.add(BIAS_DEFAULT);
		n4Weighs.add(-0.30);
		n4.setValues(n4Inputs);
		n4.setWeighs(n4Weighs);

		// External level
		final Neuron n5 = Neuron.create(TYPE);
		final Collection<Number> n5Inputs = new ArrayList<Number>();
		final Collection<Number> n5Weighs = new ArrayList<Number>();

		// W35
		n5Inputs.add(n3);
		n5Weighs.add(0.20);

		// W45
		n5Inputs.add(n4);
		n5Weighs.add(0.30);

		// W5
		n5Inputs.add(BIAS_DEFAULT);
		n5Weighs.add(-0.30);
		n5.setValues(n5Inputs);
		n5.setWeighs(n5Weighs);


		// External level
		final Neuron n6 = Neuron.create(TYPE);
		final Collection<Number> n6Inputs = new ArrayList<Number>();
		final Collection<Number> n6Weighs = new ArrayList<Number>();

		// W36
		n6Inputs.add(n3);
		n6Weighs.add(-0.20);

		// W46
		n6Inputs.add(n4);
		n6Weighs.add(-0.30);

		// W6
		n6Inputs.add(BIAS_DEFAULT);
		n6Weighs.add(0.30);
		n6.setValues(n6Inputs);
		n6.setWeighs(n6Weighs);

		// External level
		final Neuron n7 = Neuron.create(TYPE);
		final Collection<Number> n7Inputs = new ArrayList<Number>();
		final Collection<Number> n7Weighs = new ArrayList<Number>();

		// W56
		n7Inputs.add(n5);
		n7Weighs.add(0.20);

		// W67
		n7Inputs.add(n6);
		n7Weighs.add(0.30);

		// W6
		n7Inputs.add(BIAS_DEFAULT);
		n7Weighs.add(-0.30);
		n7.setValues(n7Inputs);
		n7.setWeighs(n7Weighs);

		System.out.println("Before ================");
		System.out.println("n3 = " + n3);
		System.out.println("n4 = " + n4);
		System.out.println("n5 = " + n5);
		System.out.println("n6 = " + n6);
		System.out.println("n7 = " + n7);

		printSets(x1, x2, n7);
		System.out.println("");

		double k = 1;
		int cycles = 10000;
		for (int i = 0; i < cycles; i++) {
			learn(x1, x2, n3, n4, n5, n6, n7, k);
		}

		System.out.println("After " + cycles + " cycles ================");
		System.out.println("n3 = " + n3);
		System.out.println("n4 = " + n4);
		System.out.println("n5 = " + n5);
		System.out.println("n6 = " + n6);
		System.out.println("n7 = " + n7);
		printSets(x1, x2, n7);
		System.out.println("");
	}

	private void learn(InputValue x1, InputValue x2, Neuron n3, Neuron n4, Neuron n5, Neuron n6, Neuron n7, double k) {
		for (int i = 0; i < TRAIN_SET.length; i++) {
			final double[] set = TRAIN_SET[i];
			final double[] expect = REAL_OUTPUT_SET[i];
			x1.setValue(set[0]);
			x2.setValue(set[1]);
			final double result = n7.doubleValue();
			final double error = expect[0] - result;
			n7.backpropagation(error);
		}
		n3.learn(k);
		n4.learn(k);
		n5.learn(k);
		n6.learn(k);
		n7.learn(k);
	}

	private List<Double> printSets(InputValue x1, InputValue x2, Neuron n5) {
		List<Double> errors = new ArrayList<Double>();

		for (int i = 0; i < TRAIN_SET.length; i++) {
			final double[] set = TRAIN_SET[i];
			final double[] expect = REAL_OUTPUT_SET[i];
			x1.setValue(set[0]);
			x2.setValue(set[1]);
			final double result = n5.doubleValue();
			final double e = (expect[0] - result) * (expect[0] - result);
			errors.add(e);
			System.out.println(String.format("set %d [%4.2f, %4.2f] = %9.6f expect =  %9.6f e = %9.6f", i, x1.doubleValue(), x2.doubleValue(), result, expect[0], e));
		}
		return errors;
	}
}
