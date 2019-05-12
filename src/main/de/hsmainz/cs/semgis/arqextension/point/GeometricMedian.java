package de.hsmainz.cs.semgis.arqextension.point;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns the geometric median of a MultiPoint. 
 *
 */
public class GeometricMedian extends FunctionBase1{

	double lower_limit = 0.01;
	
	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
        	throw new UnsupportedOperationException("Not supported yet.");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
	
	
	public Double sumOfDistances(Coordinate p, Coordinate[] arr, int n){ 
		double sum = 0; 
		for (int i = 0; i < n; i++) { 
			double distx = Math.abs(arr[i].getX() - p.getX()); 
			double disty = Math.abs(arr[i].getY() - p.getY()); 
			sum += Math.sqrt((distx * distx) + (disty * disty)); 
		} 
		// Return the sum of Euclidean Distances 
		return sum; 
	} 
	
	
	public void geometricMedian(Coordinate[] arr, int n) 
	{ 
	  
	    // Current x coordinate and y coordinate 
	    Coordinate current_point; 
	  
	    for (int i = 0; i < n; i++) { 
	        current_point.x += arr[i].x; 
	        current_point.y += arr[i].y; 
	    } 
	  
	    // Here current_point becomes the 
	    // Geographic MidPoint 
	    // Or Center of Gravity of equal 
	    // discrete mass distributions 
	    current_point.x /= n; 
	    current_point.y /= n; 
	  
	    // minimum_distance becomes sum of 
	    // all distances from MidPoint to 
	    // all given points 
	    double minimum_distance = sumOfDistances(current_point, arr, n); 
	  
	    int k = 0; 
	    while (k < n) { 
	        for (int i = 0; i < n && i != k; i++) { 
	            Coordinate newpoint; 
	            newpoint.x = arr[i].x; 
	            newpoint.y = arr[i].y; 
	            double newd =  sumOfDistances(newpoint, arr, n); 
	            if (newd < minimum_distance) { 
	                minimum_distance = newd; 
	                current_point.x = newpoint.x; 
	                current_point.y = newpoint.y; 
	            } 
	        } 
	        k++; 
	    } 
	  
	    System.out.println("Geometric Median = ("+current_point.x+", "+current_point.y + ")"+" with minimum distance = "+minimum_distance); 
	} 

}
