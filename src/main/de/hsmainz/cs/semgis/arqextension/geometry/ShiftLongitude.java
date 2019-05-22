package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class ShiftLongitude extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry1 = GeometryWrapper.extract(arg0);
            List<Coordinate> newcoords=new LinkedList<Coordinate>();
            for(Coordinate coord:geometry1.getXYGeometry().getCoordinates()) {
            	if(coord.getX()<0) {
            		newcoords.add(new Coordinate(coord.getX()+360,coord.getY(),coord.getZ()));
            	}else {
                	newcoords.add(coord);            		
            	}
            }
            GeometryWrapper lineStringWrapper = Force2D.createGeometry(newcoords, geometry1.getGeometryType(), geometry1);

            return lineStringWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
}