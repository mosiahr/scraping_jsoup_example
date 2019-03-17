package ua.pp.mosia.bookmaker.marafon;

import org.jsoup.nodes.Element;

public class Event {
	private String name;
	private Double price;
	private String table;

	Event(Element element) {
		scraping(element);
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getTable() {
		return table;
	}

	public void scraping(Element element) {
		try {
			String data = element.attr("data-sel").substring(1, element.attr("data-sel").length() - 1);
			String dataRes[] = data.split(",");
			for (int i = 0; i < dataRes.length; i++) {
				String dataName[] = dataRes[i].trim().split(":");

				if (dataName[0].replace("\"", "").trim().equals("sn")) {
					this.name = dataName[1].replace("\"", "");
				}
				if (dataName[0].replace("\"", "").trim().equals("epr")) {
					this.price = Double.parseDouble(dataName[1].replace("\"", ""));
				}
				if (dataName[0].replace("\"", "").trim().equals("mn")) {
					this.table = dataName[1].replace("\"", "");
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public String toString() {
		return name + ": " + price + "\t| " + table;
	}
}
