package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.jaitools.jts.LineSmoother;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class BezierSmoothing extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v,NodeValue v1) {
		GeometryWrapper geom = GeometryWrapper.extract(v);
		double alpha=v1.getDouble();
		// TODO Auto-generated method stub
		LineSmoother smoother=new LineSmoother();
		smoother.smooth((LineString)geom.getXYGeometry(), alpha);
		return null;
	}

}
