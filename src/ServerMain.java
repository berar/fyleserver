

import org.fyleserver.presenter.impl.PresenterImpl;
import org.fyleserver.server.Server;


public class ServerMain {
	public static void main(String[] args) {
		Server server = new Server(6789);
		server.run();
		PresenterImpl pres = new PresenterImpl(server);
	}
}
