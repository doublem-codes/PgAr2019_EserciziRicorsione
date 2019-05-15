/**
 * class to collect number whit is exponent
 */
public class NumbersExp {
    private double number;//numbers
    private double exp;//exponent

    /**
     * generate new class of NumbersExp
     * @param number input numbers
     * @param exp input exponent
     */
    NumbersExp(double number,double exp){
        this.exp = exp;
        this.number = number;
    }

    /**
     * method to get numbers
     * @return numbers
     */
    public double getNumber() {
        return number;
    }

    /**
     * method to get exponent
     * @return exponent
     */
    public double getExp() {
        return exp;
    }
}
