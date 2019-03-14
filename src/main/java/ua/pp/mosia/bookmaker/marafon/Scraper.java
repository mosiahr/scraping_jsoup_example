package ua.pp.mosia.bookmaker.marafon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraper {

	private int timeToWait = 3000;
	private URL url;
	private static Document page;

	Scraper(String url) throws MalformedURLException, IOException {
		setUrl(url);
		setTimeToWait(this.timeToWait);
		setPage(this.url, this.timeToWait);
	}

	Scraper(String url, int timeToWait) throws MalformedURLException, IOException {
		setUrl(url);
		setTimeToWait(timeToWait);
		setPage(this.url, this.timeToWait);
	}

	public int getTimeToWait() {
		return timeToWait;
	}

	public void setTimeToWait(int timeToWait) {
		if (timeToWait >= 0) {
			this.timeToWait = timeToWait;
		} else {
			System.out.println("Error! Time cannot be negative!");
		}
	}

	public String getUrl() {
		return url.toString();
	}

	public String getHostOfUrl() {
		return url.getProtocol() + "://" + url.getHost();
	}

	public void setUrl(String url) throws MalformedURLException {
		this.url = new URL(url);
	}

	protected static Document getPage() {
		return page;
	}

	private void setPage(URL url, int timeToWait) throws MalformedURLException, IOException {
		page = pullHtml(url, timeToWait);
	}

	public Document pullHtml(URL url, int time) throws MalformedURLException, IOException {
		Document html = Jsoup.parse(url, time);
		return html;
	}

	public static String getPageHtml() {
		return page.text();
	}

}
