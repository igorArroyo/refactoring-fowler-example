package ubu.gii.dass.refactoring;
/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5.
 * 
 * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos López</A>
 * @version 1.1
 * @see java.io.File
 * 
 */

public class Movie {
	public abstract class MoviePrice {
		public static final int CHILDRENS = 2;
		public static final int REGULAR = 0;
		public static final int NEW_RELEASE = 1;
		public abstract int getPriceCode();
	}
	
	public class NewReleasePrice extends MoviePrice{
		public int getPriceCode() {
			return NEW_RELEASE;
		}
	}
	public class ChildrenPrice extends MoviePrice {
		public int getPriceCode() {
			return CHILDRENS;
		}
	}
	public class RegularPrice extends MoviePrice {
		public int getPriceCode() {
			return REGULAR;
		}
	}


	private String _title;
	private MoviePrice _priceCode;

	public Movie(String title, int priceCode) {
		_title = title;
		setPriceCode(priceCode);	
	}

	public int getPriceCode() {
		return _priceCode.getPriceCode();
	}

	public void setPriceCode(int arg) {
		switch (arg) {
		case MoviePrice.REGULAR:
			_priceCode = new RegularPrice();
			break;
		case MoviePrice.CHILDRENS:
			_priceCode = new ChildrenPrice();
			break;
		case MoviePrice.NEW_RELEASE:
			_priceCode = new NewReleasePrice();
			break;
		default:
			throw new IllegalArgumentException("Código de precio incorrecto");
	}
	}

	public String getTitle() {
		return _title;
	}

	public double getCharge(int daysRented) {
		double result = 0;
		switch (getPriceCode()) {
		case MoviePrice.REGULAR:
			result += 2;
			if (daysRented > 2)
				result += (daysRented - 2) * 1.5;
			break;
		case MoviePrice.NEW_RELEASE:
			result += daysRented * 3;
			break;
		case MoviePrice.CHILDRENS:
			result += 1.5;
			if (daysRented > 3)
				result += (daysRented - 3) * 1.5;
			break;
		}
		return result;
	}

	public int getFrequentRenterPoints(int daysRented) {
		int frequentRenterPoints = 0;
		// add frequent renter points
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if ((getPriceCode() == MoviePrice.NEW_RELEASE)
				&& daysRented > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}
}
