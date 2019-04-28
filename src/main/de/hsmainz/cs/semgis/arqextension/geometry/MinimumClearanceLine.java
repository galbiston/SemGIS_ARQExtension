package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MinimumClearanceLine extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            org.locationtech.jts.precision.MinimumClearance clearance=new org.locationtech.jts.precision.MinimumClearance(geometry.getParsingGeometry());
            GeometryWrapper lineStringWrapper = GeometryWrapper.createGeometry(clearance.getLine(), "<http://www.opengis.net/def/crs/EPSG/0/" + geometry.getSRID() + ">", WKTDatatype.URI);
            return lineStringWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

	
}
