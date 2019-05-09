package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.operation.distance3d.Distance3DOp;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class DWithin3D extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
            Double distance=arg2.getDouble();
            GeometryWrapper transGeom2 = geom2.transform(geom1.getSrsInfo());
            return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(geom1.getXYGeometry(), transGeom2.getXYGeometry(), distance));
        } catch (DatatypeFormatException | FactoryException | MismatchedDimensionException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
