package ru.bedward70.neuron;

import junit.framework.TestCase;
import ru.bedward70.neuron.function.ResultFunctionType;
import ru.bedward70.neuron.layer.NeronLayerDescription;
import ru.bedward70.neuron.net.NeuronNet;

public class NeronNetTest extends TestCase {

	public void test() throws Exception {

		System.out.println("test");
		final NeuronNet net = NeuronNet.create(3, new NeronLayerDescription(ResultFunctionType.LINEAR, 1, true));

		System.out.println(net);
		for (final Number number : net.getOutValus()) {
			System.out.println(number.doubleValue());
		}
		net.getInputValues().get(0).setValue(5);
		System.out.println(net);
		for (final Number number : net.getOutValus()) {
			System.out.println(number.doubleValue());
		}
	}

	public void test2() throws Exception {

		System.out.println("test2");
		final NeuronNet net = NeuronNet.create(3, new NeronLayerDescription(ResultFunctionType.LINEAR, 1, true), new NeronLayerDescription(ResultFunctionType.LINEAR, 1, true));

		System.out.println(net);
		for (final Number number : net.getOutValus()) {
			System.out.println(number.doubleValue());
		}
		net.getInputValues().get(0).setValue(5);
		System.out.println(net);
		for (final Number number : net.getOutValus()) {
			System.out.println(number.doubleValue());
		}
	}
}
