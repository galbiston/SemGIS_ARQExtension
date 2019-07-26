package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYZM;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AddZ extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Double zValue=v2.getDouble();
            List<Coordinate> newcoords=new LinkedList<Coordinate>();
            for(Coordinate coord:geometry.getParsingGeometry().getCoordinates()) {
            	newcoords.add(new CoordinateXYZM(coord.x,coord.y,zValue,Double.NaN));
            }
            GeometryWrapper res=LiteralUtils.createGeometry(newcoords, geometry.getGeometryType(), geometry);
            return res.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
