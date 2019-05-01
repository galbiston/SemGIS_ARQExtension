package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

/**
 * Returns the 2-dimensional largest distance between two geometries in projected units.
 *
 */
public class MaxDistance extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
