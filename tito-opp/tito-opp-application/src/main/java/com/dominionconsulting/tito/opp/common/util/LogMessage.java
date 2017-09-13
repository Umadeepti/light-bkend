package com.dominionconsulting.tito.opp.common.util;

public class LogMessage {
	
	public static String invoked(String service, String action, String uri) {
		return prefix(service, action, uri) + "; message=Beginning service execution";
	}
	
	public static String completed(String service, String action, String uri, long time) {
		return prefix(service, action, uri) +
				"; message=Completed service execution; time=" + time + "ms";
	}
	
	private static String prefix(String service, String action, String uri) {
		return "app=tito-opp; service=" + service +
				"; action=" + action + "; uri=" + uri;
	}
	
}
