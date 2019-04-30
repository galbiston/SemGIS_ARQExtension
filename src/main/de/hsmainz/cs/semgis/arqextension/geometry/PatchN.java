package de.hsmainz.cs.semgis.arqextension.geometry;

import java.math.BigInteger;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class PatchN extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            BigInteger n = arg1.getInteger();            
            if(!geom.getGeometryType().equalsIgnoreCase("PolyhedralSurface")) {
            	return NodeValue.nvNothing;
            }else {
            	Geometry geo=geom.getGeometryN(n.intValue());
                GeometryWrapper pointWrapper = GeometryWrapper.createGeometry(geo, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
                return pointWrapper.asNodeValue();            	
            }
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
}
