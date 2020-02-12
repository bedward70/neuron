package ru.bedward70.neuron.function;

public enum ResultFunctionType {

	LINEAR(
			new MathFunction() {
				@Override
				public double apply(final double value) {
					return value;
				}
			},
			new MathFunction() {
				@Override
				public double apply(final double value) {
					return 1;
				}
			}
	),

	SIGLOG(
			new MathFunction() {
				@Override
				public double apply(final double value) {
					return (1.0 / (1.0 + Math.exp(-value)));
				}
			},
			new MathFunction() {
				@Override
				public double apply(final double value) {
					return (value * (1.0 - value));
				}
			}
	),

	HYPERTAN(
			new MathFunction() {
				@Override
				public double apply(final double value) {
					return Math.tanh(value);
				}
			},
			new MathFunction() {
				@Override
				public double apply(final double value) {
					return (1.0 / Math.pow(Math.cosh(value), 2.0));
				}
			}
	);

	private final MathFunction activation;
	private final MathFunction deactivative;

	private ResultFunctionType(final MathFunction activation, final MathFunction deactivative) {
		this.activation = activation;
		this.deactivative = deactivative;
	}

	public MathFunction getActivation() {
		return this.activation;
	}

	public MathFunction getDeactivative() {
		return this.deactivative;
	}
}
