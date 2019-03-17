package ua.pp.mosia.bookmaker.marafon;

import java.io.IOException;
import java.util.List;

public class App {

	public static void main(String[] args) throws IOException {
//		String url = "https://www.marathonbet.by/su/popular/Football/";
//		String url = "https://www.marathonbet.by/en/popular/Football/Clubs.+International/UEFA+Champions+League/";
		String url = "https://www.marathonbet.by/en/popular/Football/Clubs.+International/UEFA+Europa+League/";

//		Scraper scraper = new Scraper(url);
//		System.out.println(scraper.getTimeToWait());
//		System.out.println(scraper.getUrl());
//		System.out.println(scraper.getPage());

		ScrapPage scrapPage = new ScrapPage(url);
//		System.out.println(ScrapPage.getPage());
//		System.out.println(ScrapPage.getPageHtml());

		String header = scrapPage.getCategoryHeader();
		System.out.println(header);

		List<String> links = scrapPage.getLinks();
		for (String link : links) {
			System.out.println(link);
			Game scrapGame = new Game(link);
			System.out.println("Game: " + scrapGame.getName());
//			System.out.println(scraperEvent.getFirstPlayer());
//			System.out.println(scraperEvent.getSecondPlayer());
//			scraperEvent.getTableMatchResult();
//			scraperEvent.scrapingEvent();
			List<Event> events = scrapGame.scrapEvent();
			for (Event event : events) {
				System.out.println(event.toString());
			}
			System.out.println();
		}
	}
}