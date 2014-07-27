package com.wisedu;

import microsoft.exchange.webservices.data.*;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-27
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
public class demo {
    private final static String user = "LSGY140@outlook.com";

    private final static String password = "GFWFucker";

    public static void main(String[] args) {
        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);

        ExchangeCredentials credentials = new WebCredentials(user, password, "s.outlook.com");
        service.setCredentials(credentials);

        service.setTraceEnabled(true);
        try {
            service.autodiscoverUrl(user, new callback());
            Folder inbox = Folder.bind(service, WellKnownFolderName.Inbox);
            System.out.println(inbox.getId());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
