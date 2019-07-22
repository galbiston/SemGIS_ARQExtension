package de.hsmainz.cs.semgis.arqextension.polygon;

import java.math.BigInteger;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Checks if a point is on the edge of a polygon.
 *
 */
public class IsLocationOnEdge extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Geometry geom = geometry.getXYGeometry();
            GeometryWrapper point = GeometryWrapper.extract(v2);
            Geometry pointgeom = geometry.getXYGeometry();
            if (geom instanceof Polygon) {
            	((Polygon)geom).
            	BigInteger ringN = arg1.getInteger();
            	LineString result=((Polygon) geom).getInteriorRingN(ringN.intValue());
            	GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createGeometry(result, "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);
            	return lineStringWrapper.asNodeValue();
            }
            return NodeValue.nvNothing;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
