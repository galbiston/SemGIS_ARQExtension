package de.hsmainz.cs.semgis.arqextension.envelope;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns Z maxima of a bounding box 2d or 3d or a geometry.
 *
 */
public class ZMax extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Double currentZ=0.;
            for(Coordinate coord:geometry.getXYGeometry().getCoordinates()) {
            	if(coord.getZ()>currentZ) {
            		currentZ=coord.getZ();
            	}
            }
            return NodeValue.makeDouble(currentZ);
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
}
