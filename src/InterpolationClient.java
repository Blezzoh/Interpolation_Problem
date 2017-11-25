/**
 * Created by blaise on 11/25/17.
 * client for all interpolation classes
 */
public class InterpolationClient {
    public static void main(String [] args){
        Point [] coords = {new Point(1,3), new Point(2,2), new Point(3,5), new Point(8,10), new Point(4,7)};
        NewtonForm intplt = new NewtonForm(coords);
        LagrangeForm intplt2 = new LagrangeForm(coords);
        intplt.print();
        intplt2.print();

        intplt2.printEvaluate(1);
        intplt.printEvaluate(1);

        intplt2.printEvaluate(2);
        intplt.printEvaluate(2);

        intplt2.printEvaluate(3);
        intplt.printEvaluate(3);

        intplt2.printEvaluate(8);
        intplt.printEvaluate(8);

        intplt2.printEvaluate(4);
        intplt.printEvaluate(4);

    }
}
