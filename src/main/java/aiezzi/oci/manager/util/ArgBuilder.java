package aiezzi.oci.manager.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ArgBuilder {
	private List<String> args = new ArrayList<>();

	public ArgBuilder addWhen(Supplier<Boolean> condition, String ... arguments) {
		if (condition == null || condition.get() == null || !condition.get() || args == null || arguments == null) {
			return this;
		}

		args.addAll(Arrays.asList(arguments));

		return this;
	}

	public ArgBuilder add(String ... arguments) {
		if (arguments == null) return this;

		args.addAll(Arrays.asList(arguments));

		return this;
	}

	public String[] build() {
		return args.toArray(new String[0]);
	}
}
