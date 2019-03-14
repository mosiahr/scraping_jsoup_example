package ua.pp.mosia.bookmaker.marafon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapPage extends Scraper {

	ScrapPage(String url) throws MalformedURLException, IOException {
		super(url);
	}

	ScrapPage(String url, int timeToWait) throws MalformedURLException, IOException {
		super(url, timeToWait);
	}

	public String getCategoryHeader() {
		return getPage().select("table.category-header h1").text();
	}

	public List<String> getLinks() {
		List<String> rezult = new ArrayList<String>();
		Element divLinks = getPage().select("div.category-content div.foot-market").first();
		Elements links = divLinks.getElementsByClass("coupon-row");

		for (Element l : links) {
			String link = l.attr("data-event-page");
			rezult.add(this.getHostOfUrl() + link);
		}
		return rezult;
	}
}
