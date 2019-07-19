package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class MinimumRectangle extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(v);
	        Geometry geom = geometry.getXYGeometry();
	        org.locationtech.jts.algorithm.MinimumDiameter mindiam=new org.locationtech.jts.algorithm.MinimumDiameter(geom);
	        return GeometryWrapperFactory.createGeometry(mindiam.getMinimumRectangle(), geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    }
	}

}
