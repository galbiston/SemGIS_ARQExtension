package de.hsmainz.cs.semgis.arqextension.geometry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.io.WKBWriter;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AsSVG extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        return null;
	}

}
