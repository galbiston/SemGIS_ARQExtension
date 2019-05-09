package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns the Fréchet distance between two geometries. This is a measure of similarity between curves that takes into account the location and ordering of the points along the curves. Units are in the units of the spatial reference system of the geometries.
 *
 */
public class FrechetDistance extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
        GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
        try {
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
	        Double densifyFrac=arg2.getDouble();
        } catch (MismatchedDimensionException | TransformException | FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        throw new UnsupportedOperationException("Not supported yet.");
	}

}
