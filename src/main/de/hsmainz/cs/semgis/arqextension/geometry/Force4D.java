package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYZM;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Force4D extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getXYGeometry();
            Point point=(Point) geom;
            List<Coordinate> newcoords=new ArrayList<Coordinate>();
            for(Coordinate coord:geom.getCoordinates()) {
            	newcoords.add(new CoordinateXYZM(coord.x,coord.y,0,0));
            }         
            return Force2D.createGeometry(newcoords,geom.getGeometryType(),geometry).asNodeValue();                 
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
