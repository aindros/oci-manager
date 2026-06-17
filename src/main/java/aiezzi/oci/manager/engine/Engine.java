package aiezzi.oci.manager.engine;

import aiezzi.oci.manager.config.EngineConfig;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.io.File;
import java.io.IOException;

public abstract class Engine {
	protected @Inject EngineConfig engineConfig;

	private String ctl;
	protected String directory; /* Where to execute the command */
	private final String command;
	private boolean printCommand;

	private String[] buildCommand(String ... args) {
		/* podman|docker COMMAND ARGS */
		String[] command = new String[args.length + 2];
		int i = 0;

		command[i++] = ctl;
		command[i++] = this.command;
		for (int j = 0; j < args.length; i++, j++) {
			command[i] = args[j];
		}

		return command;
	}

	protected Engine(String command) {
		this.command = command;
	}

	@PostConstruct
	protected void init() {
		ctl = engineConfig.ctl();
		directory = engineConfig.workDirectory();
		printCommand = engineConfig.printCommand();
	}

	public int start(String ... args) throws IOException, InterruptedException {
		String[] command = buildCommand(args);
		if (printCommand) {
			System.out.println(String.join(" ", command));
		}

		Process process = new ProcessBuilder(command)
				.directory(new File(directory))
				.redirectErrorStream(true)
				.start();

		return process.waitFor();
	}
}
