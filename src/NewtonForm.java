

/**
 * Created by blaise on 11/19/17.
 * This class does the Newton form of interpolation
 * It makes the Newton polynomial through the constructor
 * the only public method is print and evaluate
 */
public class NewtonForm {
    PolynomialD poly;

    /**
     *
     * @param coords represent the array of points: (Xo, f(Xo)) ....(Xn, f(Xn))
     */
    public NewtonForm(Point [] coords ){
        PolynomialD a0 = new PolynomialD(coords[0].getY(), 0);
        PolynomialD x = new PolynomialD(1,1);
        PolynomialD last = x.minus(coords[0].getX());
        double coef = coefficient(1, a0, coords);
        poly = a0.plus(last.times(coef));

        for (int i = 2; i < coords.length; i++) {
            coef = coefficient(i, poly, coords);
            PolynomialD temp= multiplyTerms(coords, i);

            temp = temp.times(coef);
            poly = poly.plus(temp);
        }
    }

    /**
     *
     * @param coords (Xo, f(Xo)) ....(Xn, f(Xn))
     * @param i = j which is the index to stop the loop at
     * @return (X-Xo)(X-X1) .... (X-Xj-1)
     */
    private PolynomialD multiplyTerms(Point[] coords, int i) {
        PolynomialD temp = new PolynomialD(1, 1).minus(coords[0].getX());

        for (int j = 1; j <i ; j++) {
            PolynomialD temp2 = new PolynomialD(1,1).minus(coords[j].getX());
            temp = temp.times(temp2);
        }
        return temp;
    }

    /**
     * prints the polynomial
     */
    public void print(){
        System.out.println(this.poly.toString());
    }

    /**
     * returns the coefficient ai 0<i<=n
     */

    private double coefficient(int currentIndex, PolynomialD last, Point[] coords){
        int coef = multiplyDivisors(coords, currentIndex);
        return ((coords[currentIndex].getY() - last.evaluate(coords[currentIndex].getX()))/coef);
    }

    /**
     *
     * @param coords (Xo, f(Xo)) ....(Xn, f(Xn))
     * @param currentIndex = j which is the index to stop the l
     * @return (Xj-Xo)(Xj-X1) .... (Xj-Xj-1)
     */
    private int multiplyDivisors(Point [] coords, int currentIndex) {
        int sum =1;
        for (int i = 0; i < currentIndex; i++) {
            sum *= (coords[currentIndex].getX() - coords[i].getX());
        }
        return sum;
    }
    public void printEvaluate(double x){
        System.out.println("Newton   Interpolant value of " + x+ " is "+ this.evaluate(x));
    }
    /**
     * @param x
     * @return the interpolate value at x
     */
    public double evaluate(double x){
        return this.poly.evaluate(x);
    }

    

}
