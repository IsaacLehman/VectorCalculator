package com.mobiledevolpment.isaac.vectorcalculator;

import java.util.Locale;

/**
 * Created by LEHMANIT17 on 3/9/2018.
 */

public class VectorMethods {


    // methods for 3 dimensional vectors

    // a = <a1, a2, a3>
    // b = <b1, b2, b3>

    //------------------------------------------------------
    // the 2 vectors 0 = i | 1 = j | 2 = k
    public double[] aVector    = new double[3];
    public double[] bVector    = new double[3];
    public double[] aXbVector  = new double[3];
    public double[] unitVectorA = new double[3];
    public double[] unitVectorB = new double[3];
    public double[] vectorSum  = new double[3];
    public double[] vectordifference  = new double[3];

    public double aMagnitude;
    public double bMagnitude;
    public double angleBetweenR;
    public double angleBetweenD;

    public double dotProduct;
    public double vectorMagnitudeA, vectorMagnitudeB;

    public final double  PI    = 3.14159265359;

    //------------------------------------------------------

    public VectorMethods() {
    }



    public void convertAngleToRadians() {
        angleBetweenR = angleBetweenD * ( PI / 180.0 );
    }

    public void computeAngleA_B() {

        // computes angle in radians
        angleBetweenR = Math.acos(computeV_dotMagnitude(aVector, bVector) / (computeMagnitudeofV(aVector) * computeMagnitudeofV(bVector)));
        angleBetweenD = Math.toDegrees(angleBetweenR);
    }

    public String printAngleBetweenA_B() {
        return String.format(Locale.US,"\u03F4 between A & B = %.3fR = %.3f\u00b0", angleBetweenR, angleBetweenD);
    }

    public void computeCrossProduct() {
        // compute the cross product
        // i
        aXbVector[0] = (aVector[1] * bVector[2]) - (aVector[2] * bVector[1]);
        // j , The (-) term due to RHR
        aXbVector[1] = ( (-1) * ((aVector[0] * bVector[2]) - (aVector[2] * bVector[0])) );
        // k
        aXbVector[2] = (aVector[0] * bVector[1]) - (aVector[1] * bVector[0]);
    }

    public String printCrossProduct() {
        // change formating for more decimal places
        return ("A x B = " + printVector(aXbVector));
    }

    public void computeVdotProduct() {
        dotProduct = (aVector[0] * bVector[0]) + (aVector[1] * bVector[1]) + (aVector[2] * bVector[2]);
    }

    public void computeMdotProduct() {
        // must use radians, if in degrees, then use convert method
        dotProduct = (aMagnitude * bMagnitude) * Math.cos(angleBetweenR);
    }

    public String printDotProduct() {
        return String.format(Locale.US,"A \u00b7 B = %.2f", dotProduct);
    }

    public void computeMagnitude() {
        vectorMagnitudeA = Math.sqrt( Math.pow(aVector[0], 2) + Math.pow(aVector[1], 2) + Math.pow(aVector[2], 2) );
        vectorMagnitudeB = Math.sqrt( Math.pow(bVector[0], 2) + Math.pow(bVector[1], 2) + Math.pow(bVector[2], 2) );
    }

    public double computeMagnitudeofV(double[] a) {
        return Math.sqrt( Math.pow(a[0], 2) + Math.pow(a[1], 2) + Math.pow(a[2], 2) );
    }

    public String printVMagnitude() {
        return String.format(Locale.US,"|A| = %.2f\n" +
                "|B| = %.2f", vectorMagnitudeA, vectorMagnitudeB);
    }

    public void computeUnitVector() {
        computeMagnitude();

        unitVectorA[0] = aVector[0] / vectorMagnitudeA;
        unitVectorA[1] = aVector[1] / vectorMagnitudeA;
        unitVectorA[2] = aVector[2] / vectorMagnitudeA;

        unitVectorB[0] = bVector[0] / vectorMagnitudeB;
        unitVectorB[1] = bVector[1] / vectorMagnitudeB;
        unitVectorB[2] = bVector[2] / vectorMagnitudeB;
    }

    public String printUnitVector() {
        return ("Unit vector A = " + printVector(unitVectorA) + "\n" +
                "Unit vector B = " + printVector(unitVectorB));
    }

    public void addVector() {
        for (int x = 0; x < 3; x++) {
            vectorSum[x] = aVector[x] + bVector[x];
        }
    }

    public void subtractVector() {
        for (int x = 0; x < 3; x++) {
            vectordifference[x] = aVector[x] - bVector[x];
        }
    }

    public String printAplusB() {
        return ("A + B = " + printVector(vectorSum));
    }

    public String printAminusB() {
        return ("A - B = " + printVector(vectordifference));
    }

    public double computeV_dotMagnitude(double[] vectorA, double[] vectorB) {

        double dotProductM;

        // must use radians, if in degrees, then use convert method
        dotProductM = (vectorA[0] * vectorB[0]) + (vectorA[1] * vectorB[1]) + (vectorA[2] * vectorB[2]);

        return dotProductM;

    }

    public String computeLineFrom_A_to_B(double[] vectorA, double[] vectorB) {

        double[] vLineAB = new double[3];

        for (int x = 0; x < 3; x++) {
            vLineAB[x] = vectorB[x] - vectorA[x];
        }

        return ("A --> B = " + printVector(vLineAB));
    }

    public String printVector(double[] vector) {
        return String.format(Locale.US,"(%.2f) i + (%.2f) j + (%.2f) k",
                vector[0], vector[1], vector[2], vector[0], vector[1], vector[2]);
    }

}

