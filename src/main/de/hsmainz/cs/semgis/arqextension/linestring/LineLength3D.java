package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class LineLength3D extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        GeometryWrapper geometry = GeometryWrapper.extract(arg0);
        Geometry geom = geometry.getXYGeometry();
        if(geom.getGeometryType().equalsIgnoreCase("LineString") || geom.getGeometryType().equalsIgnoreCase("MultiLineString") ) {
        	LineString line=(LineString) geom;
        	//Vector3D vec=new Vector3D()
        	
        }
        throw new UnsupportedOperationException("Not supported yet."); 
	}

}
