package aiezzi.oci.manager.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "oci.engine")
public interface EngineConfig {
	@WithDefault("podman")
	String ctl();

	@WithDefault(".")
	String workDirectory();

	@WithDefault("false")
	boolean printCommand();
}
