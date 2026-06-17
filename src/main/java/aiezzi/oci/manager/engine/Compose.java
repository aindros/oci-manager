package aiezzi.oci.manager.engine;

import aiezzi.oci.manager.util.ArgBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class Compose extends Engine {
	protected Compose() {
		super("compose");
	}

	public void up(String filename, boolean daemon) throws IOException, InterruptedException {
		String[] args = new ArgBuilder()
				.addWhen(() -> filename != null && !filename.isBlank(), "-f", filename)
				.add("up")
				.addWhen(() -> daemon, "-d")
				.build();

		start(args);
	}

	public void down(String filename) throws IOException, InterruptedException {
		String[] args = new ArgBuilder()
				.addWhen(() -> filename != null && !filename.isBlank(), "-f", filename)
				.add("down")
				.build();

		start(args);
	}
}
