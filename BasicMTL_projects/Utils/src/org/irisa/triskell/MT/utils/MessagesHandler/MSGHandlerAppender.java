package org.irisa.triskell.MT.utils.MessagesHandler;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author jpthibau
 *
 * This appender stores all messages rceived from MSGHandler Logger into the compiler messages FIFO list.
 */
public class MSGHandlerAppender extends AsyncAppender {

	public MSGHandlerAppender() {
		super();
		this.name="MSGHandlerAppender";
	}
	
	public void append(LoggingEvent e) {
		String level=e.getLevel().getClass().getName();
		String msg=e.getRenderedMessage();
		String NDC=e.getNDC();
		LocationInfo locInfo=e.getLocationInformation();
		if (MSGHandler.allMessages == null) MSGHandler.init();
		MSGHandler.allMessages.add(new CompilerMessage(level,msg,NDC,locInfo.getFileName(),locInfo.getClassName(),locInfo.getMethodName(),locInfo.getLineNumber()));	}
}
