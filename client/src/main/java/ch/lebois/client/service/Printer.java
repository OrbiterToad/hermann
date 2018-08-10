package ch.lebois.client.service;

import ch.lebois.client.handler.ResponseSender;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class Printer {

    public void getPrinters() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService printer : printServices) {
            new ResponseSender("response", printer.getName());
        }
        new ResponseSender("response", "Number of print services: " + printServices.length);
    }
}
