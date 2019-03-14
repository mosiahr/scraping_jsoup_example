package ua.pp.mosia.bookmaker.marafon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScraperItem extends Scraper {
	private String nameEvent = "";
	private List<String> nameTables = null;

	ScraperItem(String url) throws MalformedURLException, IOException {
		super(url);
		this.nameEvent = scrapNameEvent();
		this.nameTables = scrapNameTables();
	}

	ScraperItem(String url, int timeToWait) throws MalformedURLException, IOException {
		super(url, timeToWait);
		this.nameEvent = scrapNameEvent();
		this.nameTables = scrapNameTables();
	}

	private String scrapNameEvent() {
		String nameEvent = "";
		try {
			Element element = getPage().select("table.member-area-content-table").first();
			Elements tds = element.select("td span");
			for (Element el : tds) {
				nameEvent += el.text() + " - ";
			}
			nameEvent = nameEvent.substring(0, nameEvent.length() - 2).trim();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return nameEvent;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public List<String> scrapNameTables() {
		List<String> nameTables = new ArrayList<String>();
		try {
			Elements tables = getPage().select("table.market-table-name");
			for (Element el : tables) {
				nameTables.add(el.select("div.name-field").text());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return nameTables;
	}

	public List<String> getNameTables() {
		return nameTables;
	}

	public void getTableResult(List<String> nameTables) {

	}

}
