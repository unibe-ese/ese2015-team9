package team9.tutoragency.controller.service;

public class OfferService {
	/**
	 * Calculates grade/4 and returns a string representation of the double result value.
	 * Used to store the grade as int value (i.e 16 -> "4.0", 17->"4.25").
	 * @param grade
	 * @return
	 */
	public String gradeToString(int grade){
		Double result = (double) grade/4;
		return result.toString();
	}
	
	/**
	 * Returns the gradeString as double multiplied with 4. (i.e. "4.25"->17, "4.0"->16).
	 * @param gradeString, has to be a representation of a floating point value(e.g "4.0")
	 * @return
	 */
	public int stringToGrade(String gradeString) throws NumberFormatException{
		double result = Double.parseDouble(gradeString);
		return (int) (result*4);
		
	}
}

