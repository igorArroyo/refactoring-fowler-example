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
		public int getFrequentRenterPoints(int daysRented) {
			return 1;
		}
		public abstract double getCharge(int daysRented);
	}
	
	public class NewReleasePrice extends MoviePrice{
		public int getPriceCode() {
			return NEW_RELEASE;
		}
		public double getCharge(int daysRented) {
			return daysRented * 3;
		}
		@Override
		public int getFrequentRenterPoints(int daysRented) {
			return (daysRented > 1) ? 2 : 1;
		}
	}
	public class ChildrenPrice extends MoviePrice {
		public int getPriceCode() {
			return CHILDRENS;
		}
		
		public double getCharge(int daysRented) {
			double result = 1.5;
			if (daysRented > 3)
				result += (daysRented - 3) * 1.5;
			return result;
		}
	}
	public class RegularPrice extends MoviePrice {
		public int getPriceCode() {
			return REGULAR;
		}
		public double getCharge(int daysRented) {
			double result = 2;
			if (daysRented > 2)
				result += (daysRented - 2) * 1.5;
			return result;
		}
	}


	private String _title;
	MoviePrice _priceCode;

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
		return _priceCode.getCharge(daysRented);
	}

	public int getFrequentRenterPoints(int daysRented) {
		return _priceCode.getFrequentRenterPoints(daysRented);
	}
}
