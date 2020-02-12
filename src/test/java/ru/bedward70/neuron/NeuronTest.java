package ru.bedward70.neuron;

import junit.framework.TestCase;
import ru.bedward70.neuron.function.ResultFunctionType;

import java.util.ArrayList;

public class NeuronTest extends TestCase {

	public void test() throws Exception {
		final Neuron neuron = Neuron.create(ResultFunctionType.LINEAR);
		neuron.setWeighs(new ArrayList<Number>() {{add(5.);add(4.);}});
		neuron.setValues(new ArrayList<Number>() {{add(2.);add(3.);}});

		assertEquals(22., neuron.doubleValue());
	}

	public void testBackpropagation() throws Exception {
		final Neuron neuron = Neuron.create(ResultFunctionType.LINEAR);
		neuron.setWeighs(new ArrayList<Number>() {{add(5.);add(4.);}});
		neuron.setValues(new ArrayList<Number>() {{add(2.);add(3.);}});

		assertEquals(22., neuron.doubleValue());

		neuron.backpropagation(1);
		neuron.learn(1);
		assertEquals(35., neuron.doubleValue());
	}

	public void testBackpropagation2() throws Exception {
		final Neuron neuron = Neuron.create(ResultFunctionType.LINEAR);
		neuron.setWeighs(new ArrayList<Number>() {{add(5.);add(4.);}});
		neuron.setValues(new ArrayList<Number>() {{add(2.);add(3.);}});

		final Neuron neuron2 = Neuron.create(ResultFunctionType.LINEAR);
		neuron2.setWeighs(new ArrayList<Number>() {{add(5.);}});
		neuron2.setValues(new ArrayList<Number>() {{add(neuron);}});

		assertEquals(110., neuron2.doubleValue());

		neuron2.backpropagation(1);
		neuron2.learn(1);
		assertEquals(594., neuron2.doubleValue());
		neuron.learn(1);
		assertEquals(2349., neuron2.doubleValue());
	}
}
