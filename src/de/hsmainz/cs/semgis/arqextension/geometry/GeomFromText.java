package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.parsers.wkt.WKTReader;

public class GeomFromText extends FunctionBase1{
	
	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            String wktstring=arg0.getString();
            WKTReader wktreader=WKTReader.extract(wktstring);
            Geometry geom=wktreader.getGeometry();
            GeometryWrapper pointWrapper = GeometryWrapper.createGeometry(geom, "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
            return pointWrapper.asNodeValue();           
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}


}
