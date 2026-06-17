package aiezzi.oci.manager.controller;

import aiezzi.oci.manager.engine.Compose;
import aiezzi.oci.manager.util.RunnableException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/compose")
public class ComposeController {
	private @Inject Compose compose;

	private void execute(RunnableException cmd) {
		try {
			cmd.run();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Path("/{composeName}/up")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String up(@PathParam("composeName") String composeName) {
		execute(() -> compose.up(composeName, true));

		return "Up.";
	}

	@Path("/{composeName}/down")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String down(@PathParam("composeName") String composeName) {
		execute(() -> compose.down(composeName));

		return "Down.";
	}
}
