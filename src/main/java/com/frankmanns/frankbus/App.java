package com.frankmanns.frankbus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class App 
{
    public static void main(String[] args)
    {
        String url = "http://avlweb.charlottesville.org/RTT/Public/RoutePositionET.aspx?PlatformNo=11983";

        Document document;
        try {
            document = Jsoup.connect(url).get();
        }
        catch (IOException e) {
            return;
        }
        Elements rows = document.select(".tableET tbody tr:gt(0)");

        for(Element row : rows) {
            Elements data = row.select("td");
            String route = data.get(0).text();
            String destination = data.get(1).text();
            Integer eta;
            try {
                eta = Integer.parseInt(data.get(2).text());;
            }
            catch (NumberFormatException e) {
                continue;
            }
            System.out.println(String.format("Route: %s; Destination: %s; ETA: %d", route, destination, eta));
        }
    }
}
