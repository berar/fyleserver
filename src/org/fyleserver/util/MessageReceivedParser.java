package org.fyleserver.util;

import org.fyleserver.util.mrhandler.MessageReceivedHandler;

public interface MessageReceivedParser {
	MessageReceivedHandler handle();
}
