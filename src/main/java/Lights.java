/**
 * Illustrative simple Java class
 */
public class Lights {
	private final Color indicator;
	private final GoodBad builtinTest;
	private final float temperature;
	private final boolean on;

	public Lights(Color indicator, GoodBad builtinTest, float temperature,
			boolean on) {
		this.indicator = indicator;
		this.builtinTest = builtinTest;
		this.temperature = temperature;
		this.on = on;
	}

	@Override
	public String toString() {
		return "Lights [indicator=" + indicator + ", builtinTest="
				+ builtinTest + ", temperature=" + temperature + ", on=" + on
				+ "]";
	}
}
