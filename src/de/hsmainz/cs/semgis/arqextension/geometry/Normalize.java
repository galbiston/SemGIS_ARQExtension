package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Normalize extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            GeometryWrapper simpleWrapper = GeometryWrapper.createGeometry(geometry.getXYGeometry().norm(), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
            return simpleWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
