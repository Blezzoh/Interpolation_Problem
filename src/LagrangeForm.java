/**
 * Created by blaise on 11/25/17.
 * This class does the Lagrange form of interpolation
 * It makes the Lagrange polynomial through the constructor
 * the only public method is print and evaluate
 */
public class LagrangeForm {
    PolynomialD poly =null;
    public LagrangeForm(Point [] p){
        for (int i = 0; i <p.length ; i++) {
            PolynomialD temp = l(p,i).times(p[i].getY());
            if(poly == null){
                poly = temp;
            }
            else{
                poly = poly.plus(temp);
            }
        }
    }

    /**
     * Calculates the Kronecker delta
     * @param p interpolation points
     * @param iSkip index j to skip while multiplying terms on the denominator and numerator
     * @return (X-X0) ....(X - Xj-1)(X - Xj+1) ... (X-Xn)/(Xj-Xo) ...(Xj - Xj-1)(Xj - Xj+1) ... (Xj-Xn)
     */
    private PolynomialD l(Point [] p, int iSkip){
        PolynomialD l = multiplyTerms(p, iSkip);
        double coef = 1/multiplyDivisors(p, iSkip);
        l = l.times(coef);
        return l;
    }

    /**
     * Calculates the Kronecker delta denominator
     * @param p interpolation points
     * @param iSkip index j to skip while multiplying terms on the denominator
     * @return (Xj-Xo) ...(Xj - Xj-1)(Xj - Xj+1) ... (Xj-Xn)
     */
    private double multiplyDivisors(Point[] p, int iSkip) {
        double lDivisor = 1;
        for (int i = 0; i < p.length; i++) {
            if(i != iSkip){
                lDivisor *= (p[iSkip].getX() - p[i].getX());
            }
        }
        return lDivisor;
    }

    /**
     * calculates the Kronecker delta numerator
     * @param p interpolation points
     * @param iSkip index j to skip while multiplying terms on the numerator
     * @return (X-X0) ....(X - Xj-1)(X - Xj+1) ... (X-Xn)
     */
    private PolynomialD multiplyTerms(Point[] p, int iSkip) {
        PolynomialD lNumerator = null;
        for (int i = 0; i <p.length ; i++) {
            if(i != iSkip){
                if(lNumerator == null){
                    lNumerator = new PolynomialD(1,1).minus(p[i].getX());
                }
                else{
                    PolynomialD temp = new PolynomialD(1,1).minus(p[i].getX());
                    lNumerator = lNumerator.times(temp);
                }
            }
        }
        return lNumerator;
    }
    public void print(){
        System.out.println(this.poly.toString());
    }
    public double evaluate(double x){
        return this.poly.evaluate(x);
    }
    public void printEvaluate(double x){
        System.out.println("Lagrange Interpolant value of " + x+ " is "+ this.evaluate(x));
    }
}
