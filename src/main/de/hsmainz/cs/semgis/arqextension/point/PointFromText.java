package de.hsmainz.cs.semgis.arqextension.point;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.parsers.wkt.WKTReader;

/**
 * Makes a point Geometry from WKT with the given SRID. If SRID is not given, it defaults to unknown.
 *
 */
public class PointFromText extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            String wktstring=arg0.getString();
            WKTReader wktreader=WKTReader.extract(wktstring);
            Geometry geom=wktreader.getGeometry();     
            if("POINT".equals(geom.getGeometryType().toUpperCase())){
            	GeometryWrapper pointWrapper = GeometryWrapper.createPoint(geom.getCoordinate(), "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
                return pointWrapper.asNodeValue();
            }else {
            	throw new ExprEvalException("WKT does not represent a point", null);
            }
            
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
