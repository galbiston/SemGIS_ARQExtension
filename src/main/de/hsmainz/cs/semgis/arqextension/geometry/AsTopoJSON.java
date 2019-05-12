package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class AsTopoJSON extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            throw new UnsupportedOperationException("Not supported yet.");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}