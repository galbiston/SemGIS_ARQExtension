package de.hsmainz.cs.semgis.arqextension.geometry;

import java.math.BigInteger;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns a Geometry in X3D xml node element format: ISO-IEC-19776-1.2-X3DEncodings-XML
 *
 */
public class AsX3D extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Geometry geom = geometry.getXYGeometry();
            BigInteger maximaldecimaldigits=v2.getInteger();
            BigInteger options=v3.getInteger();
    		throw new UnsupportedOperationException("Not supported yet.");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }

	}

}
