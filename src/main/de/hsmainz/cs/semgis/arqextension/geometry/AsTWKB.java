package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.List;

import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

/**
 * Returns the geometry as TWKB, aka "Tiny Well-Known Binary"
 *
 */
public class AsTWKB extends FunctionBase {

	@Override
	public NodeValue exec(List<NodeValue> args) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void checkBuild(String uri, ExprList args) {
		// TODO Auto-generated method stub
		
	}

}
