package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.BoundaryOp;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Force2D extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getXYGeometry();
            List<Coordinate> newcoords=new ArrayList<Coordinate>();
            for(Coordinate coord:geom.getCoordinates()) {
            	newcoords.add(new Coordinate(coord.x,coord.y));
            }
            return createGeometry(newcoords,geom.getGeometryType(),geometry).asNodeValue();           
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
	
	public static GeometryWrapper createGeometry(List<Coordinate> coordinates,String geomtype,GeometryWrapper geometry) {
		switch(geomtype) {
		case "Point":
			return GeometryWrapper.createPoint(coordinates.get(0), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
		case "MultiPoint":
			return GeometryWrapper.createMultiPoint(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
		case "LineString":
			return GeometryWrapper.createLineString(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
		case "Polygon":
			return GeometryWrapper.createPolygon(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
		case "MultiLineString":
			return GeometryWrapper.createMultiLineString(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
		case "MultiPolygon":
			return GeometryWrapper.createMultiPolygon(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
		}
	}

}
