package ua.pp.mosia.bookmaker.marafon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Game extends Scraper {
	private String firstPlayer;
	private String secondPlayer;
	private String name;
	private List<String> nameTables = scrapNameTables();

	Game(String url) throws MalformedURLException, IOException {
		super(url);
		scrapGame();
	}

	Game(String url, int timeToWait) throws MalformedURLException, IOException {
		super(url, timeToWait);
		scrapGame();
	}

	public String getFirstPlayer() {
		return firstPlayer;
	}

	public String getSecondPlayer() {
		return secondPlayer;
	}

	public String getName() {
		return name;
	}

	private String setName(String separator) {
		return firstPlayer + separator + secondPlayer;
	}

	public List<String> getNameTables() {
		return nameTables;
	}

	private void scrapGame() {
		try {
			Element element = getPage().select("table.member-area-content-table").first();
			this.firstPlayer = element.select("td span").get(0).text();
			this.secondPlayer = element.select("td span").get(1).text();
			this.name = setName(" - ");
		} catch (Exception e) {
			e.getStackTrace();
		}
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

	public List<Event> scrapEvent() {
		List<Event> events = new ArrayList<Event>();
		try {
			List<Element> elements = getPage().select("td[data-header-highlighted=header-highlighted]");
			for (Element el : elements) {
				Event event = new Event(el);
				events.add(event);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return events;
	}
}
